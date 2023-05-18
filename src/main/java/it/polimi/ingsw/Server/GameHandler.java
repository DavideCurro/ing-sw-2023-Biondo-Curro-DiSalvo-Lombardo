package it.polimi.ingsw.Server;

import it.polimi.ingsw.Controller.Match;
import it.polimi.ingsw.Controller.MatchExeception;
import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.Player.Player;

import it.polimi.ingsw.Utility.Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import java.util.logging.Logger;

import static it.polimi.ingsw.Utility.Message.Content.*;


public class GameHandler implements Runnable {
    private final ConnectionType[] players;
    private final String[] usernames;

    private final ObjectInputStream[] inputStreams;
    private final ObjectOutputStream[] objectOutputStreams;
    private final Match match;
    private final Vector<String> validName;
    private Message message;
    private boolean response;
    private final NetworkSniffer sniffer;
    private static final Logger log = Logger.getLogger(GameHandler.class.getName());

    /**
     * This is a constructor
     * @param players Socket[]
     * @param usernames String[]
     * @param objectOutputStreams ObjectOutputStream[]
     * @param objectInputStream ObjectInputStream[]
     */
    public GameHandler(ConnectionType[] players, String[] usernames, ObjectOutputStream[] objectOutputStreams, ObjectInputStream[] objectInputStream) {
        this.players = players;
        this.usernames = usernames;
        this.objectOutputStreams = objectOutputStreams;
        this.inputStreams = objectInputStream;
        match = new Match();
        validName = new Vector<>();
        response = false;
        sniffer = new NetworkSniffer(inputStreams,this);
    }



    /**
     * Fast close
     * @throws IOException, socket
     */
    private void closeAllConnection() throws IOException {
        for(ConnectionType connectionType : players){
                connectionType.close();
        }
        sniffer.interrupt();
    }

    /**
     * Validate all nickname, check for duplicate
     *
     * @return true if all player are valid, false otherwise
     */
    private boolean validatePlayer(){
        for(int i = 0; i< usernames.length; i++){ //loop all player
            if(invalid_Name(usernames[i])){ //this player has already logged?
                do {
                    log.warning("Same Nickname");
                    try {
                        this.objectOutputStreams[i].writeObject(new Message(usernames[i], NICKNAME_DUPLICATE)); //Ask for new nickname
                    }catch (IOException e){
                        log.severe("ERROR CLIENT NOT RESPONDING");
                        return false;
                    }
                    try {
                        message = (Message) this.inputStreams[i].readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        log.severe("ERROR CLIENT NOT RESPONDING");
                        try {
                            closeAllConnection();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        return false;
                    }
                    usernames[i] = (String) message.getPayload();
                }while(invalid_Name(usernames[i]));
            }
        }
        return true;
    }
    private void startGame(){
        for(int i = 0; i<usernames.length;i++){

            if(players[i].isSocket()){
                try {
                    objectOutputStreams[i].flush();
                    objectOutputStreams[i].reset();
                    objectOutputStreams[i].writeObject(new Message(usernames[i], NEWGAME,match.getP()));
                    objectOutputStreams[i].writeObject(new Message(usernames[i], PLAYERDATA,match.getThisPlayer(usernames[i])));
                    objectOutputStreams[i].writeObject(new Message(usernames[i],"Server", COMMONOBJ,match.getCommonOBJ1(),match.getCommonOBJ2()));
                    log.info("Info sent to "+ usernames[i]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                players[i].setGameHandler(this);
                players[i].unLock();
            }
        }
    }

    /**
     * This method is the kernel of game logic
     */
    @Override
    public void run() {
        log.info("Game for started");
        if(!validatePlayer()) return; //if there's something wrong close this lobby
        setPlayer();
        match.setupPlayground();
        log.info("New Match started");
        startGame();
        sniffer.start(); //start the sniffer to catch all message in this socket. It's a thread.
        do{
            gamePhasePlaying();
        }while(endGame() == -1);
        log.info("The game is about to finish");
        while(!getNowPlaying().getIs_second()){
            gamePhasePlaying();
        }
        match.calculateADJ();
        try {
            log.info("The game is finish");
            sniffer.interrupt();
            sendEndGameMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("GoodBye");
        try {
            closeAllConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * getThisPlayer is usefully for get the player just knowing his name
     * @param nick, is the nickname of player
     * @return player or null
     */
    private int getUsernameIndex(String nick){
        for(int i = 0;i<usernames.length;i++){
            if(usernames[i].equals(nick)) return i;
        }
        return -1;
    }
    private void gamePhasePlaying(){
        int nowPlaying = getUsernameIndex(getNowPlaying().getNickname()); //Looking for who is playing
        log.info(usernames[nowPlaying]+ " is playing");
        try {
            sendMessage(nowPlaying); //Looking for him choose
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        waitResponse();
    }
    /**
     * SendMessage ask player who's playing to send all the pick-up information
     * @param nowPlaying who's playing
     * @throws IOException if objectOutputStreams get some error
     */
    private void sendMessage(int nowPlaying) throws IOException {
        objectOutputStreams[nowPlaying].writeObject(new Message(usernames[nowPlaying],PICKTILE));
    }


    private void sendEndGameMessage()throws IOException{
        for(ObjectOutputStream outputStream : objectOutputStreams){
            resetObject(outputStream);
            outputStream.writeObject(new Message("","server",ENDGAME,match.getPlayer(),match.getWinner()));
        }
    }

    /**
     * endGame just check when the game is about to finish
     *
     * @return if the last player has completed his shelf
     */
    private int endGame(){
        if(match.getLastPlayer().getMy_shelfie().isFull()) {
            match.getLastPlayer().setPoints(1);
            return match.detectEndGame();
        }
        return -1;
    }

    /**
     * WaitResponse stall all thread from this lobby
     */
    private synchronized void waitResponse(){
        while(!response){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        response = false;
    }


    /**
     * valid_name check if this username has been already used
     * @param name String
     * @return true if is valid
     */
    private boolean invalid_Name(String name){
        if(validName.contains(name)) return true;
        return !validName.add(name);
    }

    /**
     * setPlayer update model with the new player information
     */
    private void setPlayer(){
        for(String tmp : usernames) {
            try {
                match.newPlayer(tmp);
            }catch (MatchExeception exception){
                log.warning("ERROR In add player");
            }
        }

    }



    /**
     * getNowPlaying
     * @return who is the current player
     */
    private Player getNowPlaying(){
        return this.match.getNowPlaying();
    }


    /**
     * setResponse notify all thread to unlock their wait
     */
    private void setResponse(){
        response = true;
        notifyAll();
        log.info("Turn went ok");
    }

    /**
     * sendMatch will send all model information to all player
     */
    private synchronized  void sendMatch(){
        for(ObjectOutputStream objectOutputStream: objectOutputStreams){
            try {
                objectOutputStream.reset();
                objectOutputStream.writeObject(new Message("","server", PICKEDTILE, match.getP(),match.getLastPlayer()));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * notifyPersonalOBJDone, this method notify just the player who did this goal
     * @param index of the player
     */
    private void notifyPersonalOBJDone(int index){
        try {
            objectOutputStreams[index].writeObject(new Message(usernames[index],"Server", PERSONALOBJDONE, match.getThisPlayer(usernames[index]),match.getThisPlayer(usernames[index]).checkPersonalOBJ()));
        }catch (IOException e){
            log.severe("can not connect to client");
        }
    }

    /**
     * notifyCommonOBJDone, this method notify all player that some player has done the goal, and he is the #
     * @param player who has made goal
     * @param countOBJ how many people made this goal, including last one
     */
    private void notifyCommonOBJDone(Player player, int[] countOBJ){

        for(ObjectOutputStream objectOutputStream : objectOutputStreams){
            resetObject(objectOutputStream);
            try {
                objectOutputStream.writeObject(new Message("", "Server", COMMONOBJDONE, player, countOBJ));
            }catch (IOException e){
                e.printStackTrace();
                log.severe("can not connect to client");
            }
        }
    }

    /**
     * handleTurn handle all pickUp problem and possible result
     * @param column is the column of library
     * @param tiles is the set of tiles that the client want to pickup
     * @param sender who is picking this tiles
     */
    public synchronized void handleTurn(int column, Vector<Tiles> tiles, String sender) {
        Player nowPlaying = getNowPlaying();
        int index = getUsernameIndex(sender);
        if(nowPlaying.getNickname().equals(sender)){//Check if the turn is correct
            log.info("Client who's playing is correct");
            int response = match.newTurn(column,tiles);
           if( response == 0){
               log.info("Everything in this turn went well");
               setResponse();
               log.info("Sending new information");
               sendMatch();
               log.info("Retrieving information for Object test ");
               int[] commonOBJResponse = match.commonOBJTesting(nowPlaying);
               if(commonOBJResponse[0] == 1){
                   log.info(nowPlaying.getNickname() + "has done common OBJ");
                   notifyCommonOBJDone(nowPlaying,commonOBJResponse);
               }
               int privatePoint = nowPlaying.checkPersonalOBJ();
               if(privatePoint > 0){
                   nowPlaying.setPrivatePoints(nowPlaying.getPrivatePoints()+privatePoint);
                   log.info(nowPlaying.getNickname() + "has done personal OBJ");
                   resetObject(objectOutputStreams[index]);
                   notifyPersonalOBJDone(index);
               }
           }else if(response == 1) {
                log.warning("Pick-Up issue");
                match.resetPlayers();
                try {
                    objectOutputStreams[index].writeObject(new Message(sender, PICKUPFAIL));
                }catch (IOException e){
                    e.printStackTrace();
                }
           }else{
               log.severe("Something went wrong, that's a big problem!");
               try {
                   objectOutputStreams[index].writeObject(new Message(sender, FAIL));
                   closeAllConnection();
               }catch (IOException e){
                   e.printStackTrace();
               }
           }
        }else{
            log.warning("Wrong player");
            if(index != -1){
                try {
                    objectOutputStreams[index].writeObject(new Message(sender, WRONG_PLAYER));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else{
                log.severe("ERROR UNKNOWN PLAYER");
            }
        }

    }

    /**
     * reset output stream
     * @param outputStream, outputstream
     */
    private void resetObject(ObjectOutputStream outputStream){
        try {
            outputStream.flush();
            outputStream.reset();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public Match getMatch(){return match;}

}
