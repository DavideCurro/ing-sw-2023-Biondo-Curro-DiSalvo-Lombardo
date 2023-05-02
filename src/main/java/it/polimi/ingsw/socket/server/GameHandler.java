package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.controller.MatchExeception;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Player;

import it.polimi.ingsw.socket.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Logger;

import static it.polimi.ingsw.socket.Content.*;


public class GameHandler implements Runnable {
    private Socket[] players;
    private String[] usernames;

    private ObjectInputStream[] inputStreams;
    private ObjectOutputStream[] objectOutputStreams;
    private Match match;
    private Vector<String> validName;
    private Message message;
    private boolean response;
    private NetworkSniffer sniffer;

    private static final Logger log = Logger.getLogger(GameHandler.class.getName());

    /**
     * This is a constructor
     * @param players Socket[]
     * @param usernames String[]
     * @param objectOutputStreams ObjectOutputStream[]
     * @param objectInputStream ObjectInputStream[]
     */
    public GameHandler(Socket[] players, String[] usernames, ObjectOutputStream[] objectOutputStreams, ObjectInputStream[] objectInputStream) {
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
        for(Socket socket : players){
                socket.close();
        }
    }

    /**
     * Validate all nickname, check for duplicate
     *
     * @return true if all player are valid, false otherwise
     */
    private boolean validatePlayer(){
        for(int i = 0; i< usernames.length; i++){ //loop all player
            if(!valid_Name(usernames[i])){ //this player has already logged?
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
                }while (!valid_Name(usernames[i]));
            }
        }
        return true;
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
        for(int i = 0;i< usernames.length;i++){ //Notify everyone their data and the playground
            try {
                objectOutputStreams[i].flush();
                objectOutputStreams[i].reset();
                objectOutputStreams[i].writeObject(new Message(usernames[i], NEWGAME,match.getP()));
                objectOutputStreams[i].writeObject(new Message(usernames[i], PLAYERDATA,getThisPlayer(i)));
                objectOutputStreams[i].writeObject(new Message(usernames[i], COMMONOBJ,match.getCommonOBJ()));
                log.info("Info sent to "+ usernames[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        sniffer.start(); //start the sniffer to catch all message in this socket. It's a thread.
        do{
            int nowPlaying = getThisPlayer(getNowPlaying().getNickname()); //Looking for who is playing
            log.info(usernames[nowPlaying]+ " is playing");
            try {
                sendMessage(nowPlaying); //Looking for him choose
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            waitResponse();
        }while(!endGame());
        // ENDGAME
    }

    /**
     * SendMessage ask player who's playing to send all the pick-up information
     * @param nowPlaying who's playing
     * @throws IOException if objectOutputStreams get some error
     */
    private void sendMessage(int nowPlaying) throws IOException {
        objectOutputStreams[nowPlaying].writeObject(new Message(usernames[nowPlaying],PICKTILE));
    }

    /**
     * endGame just check when the game is about to finish
     * @return if the last player has completed his shelf
     */
    private boolean endGame(){
        return match.getLastPlayer().getMy_shelfie().isFull();
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
    private boolean valid_Name(String name){
        if(validName.contains(name)) return false;
        return validName.add(name);
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
     * getThisPlayer is usefully for get the player just knowing his index
     * @param i, is the index
     * @return player or null
     */
    private synchronized Player getThisPlayer(int i){
        LinkedList<Player> tmp = match.getPlayer();
        for(Player player : tmp){
            if(player.getNickname().equals(usernames[i])){
                return player;
            }
        }
        return null;
    }

    /**
     * getThisPlayer is usefully for get the player just knowing his name
     * @param name, is the nickname of player
     * @return player or null
     */
    private synchronized int getThisPlayer(String name){
        for(int i = 0; i< usernames.length; i++){
            if(usernames[i].equals(name)){
                return i;
            }
        }
        return -1;
    }

    /**
     * getNowPlaying
     * @return who is the current player
     */
    private Player getNowPlaying(){
        return this.match.getNowPlaying();
    }

    /**
     * getMatch
     * @return model
     */
    public Match getMatch(){return match;}

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
                objectOutputStream.writeObject(new Message("","server", PICKEDTILE, match.getP(),match.getPlayer()));
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
            objectOutputStreams[index].writeObject(new Message(getThisPlayer(index).getNickname(),"Server", PERSONALOBJDONE,getThisPlayer(index),getThisPlayer(index).checkPersonalOBJ()));
        }catch (IOException e){
            log.severe("can not connect to client");
        }
    }

    /**
     * notifyCommonOBJDone, this method notify all player that some player has done the goal, and he is the #
     * @param player who has made goal
     * @param countOBJ how many people made this goal, including last one
     */
    private void notifyCommonOBJDone(Player player, int countOBJ){

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
        int index = getThisPlayer(sender);
        Player nowPlaying = getNowPlaying();
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
                   notifyCommonOBJDone(nowPlaying,commonOBJResponse[1]);
               }
               if(nowPlaying.checkPersonalOBJ() > 0){
                   nowPlaying.setPoints(nowPlaying.getPoints()+nowPlaying.checkPersonalOBJ());
                   log.info(nowPlaying.getNickname() + "has done personal OBJ");
                   resetObject(objectOutputStreams[index]);
                   notifyPersonalOBJDone(index);
               }
           }else if(response == 1) {
                log.warning("Pick-Up issue");
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
    private void resetObject(ObjectOutputStream outputStream){
        try {
            outputStream.flush();
            outputStream.reset();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
