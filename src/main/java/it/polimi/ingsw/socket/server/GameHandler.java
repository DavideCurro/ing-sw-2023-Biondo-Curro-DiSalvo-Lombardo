package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.controller.MatchExeception;
import it.polimi.ingsw.controller.VirtualView;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.socket.Content;
import it.polimi.ingsw.socket.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Logger;

public class GameHandler implements Runnable {
    private Socket[] players;
    private String[] usernames;

    private ObjectInputStream[] inputStreams;
    private ObjectOutputStream[] objectOutputStreams;
    private Match match;
    private Controller controller;
    private Vector<String> validName;
    private Message message;
    private boolean response;
    private NetworkSniffer sniffer;

    private static final Logger log = Logger.getLogger(GameHandler.class.getName());
    public GameHandler(Socket[] players, String[] usernames, ObjectOutputStream[] objectOutputStreams, ObjectInputStream[] objectInputStream) {
        this.players = players;
        this.usernames = usernames;
        this.objectOutputStreams = objectOutputStreams;
        this.inputStreams = objectInputStream;
        match = new Match();
        controller = new Controller(match, new VirtualView());
        validName = new Vector<>();
        response = false;
        sniffer = new NetworkSniffer(players,inputStreams,this);
    }
    private void closeAllConnection() throws IOException {
        for(Socket socket : players){
                socket.close();
        }
    }
    private boolean validatePlayer(){
        for(int i = 0; i< usernames.length; i++){
            if(!valid_Name(usernames[i])){
                do {
                    log.warning("Same Nickname");
                    try {
                        this.objectOutputStreams[i].writeObject(new Message(usernames[i], Content.FAIL));
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
    @Override
    public void run() {
        log.info("Game for started");
        if(!validatePlayer()) return;
        setPlayer();
        match.setupPlayground(2);
        log.info("New Match started");
        for(int i = 0;i< usernames.length;i++){
            try {
                objectOutputStreams[i].flush();
                objectOutputStreams[i].reset();
                objectOutputStreams[i].writeObject(new Message(usernames[i], Content.NEWGAME,match.getP()));
                objectOutputStreams[i].writeObject(new Message(usernames[i], Content.PLAYERDATA,getThisPlayer(i)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        sniffer.start();
        do{
            int nowPlaying = getThisPlayer(getNowPlaying().getNickname());
            log.info(usernames[nowPlaying]+ " is playing");
            try {
                sendMessage(nowPlaying);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            waitResponse();
        }while(!endGame());
        // ENDGAME
    }

    private void sendMessage(int nowPlaying) throws IOException {
        objectOutputStreams[nowPlaying].writeObject(new Message(usernames[nowPlaying],Content.PICKTILE));
    }
    private boolean endGame(){
        return match.getLastPlayer().getMy_shelfie().isFull();
    }
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


    private boolean valid_Name(String name){
        if(validName.contains(name)) return false;
        return validName.add(name);
    }
    private void setPlayer(){
        for(String tmp : usernames) {
            try {
                match.newPlayer(tmp);
            }catch (MatchExeception exception){
                log.warning("ERROR In add player");
            }
        }

    }
    private synchronized Player getThisPlayer(int i){
        LinkedList<Player> tmp = match.getPlayer();
        for(Player player : tmp){
            if(player.getNickname().equals(usernames[i])){
                return player;
            }
        }
        return null;
    }
    private synchronized int getThisPlayer(String name){
        LinkedList<Player> tmp = match.getPlayer();
        for(int i = 0; i< usernames.length; i++){
            if(usernames[i].equals(name)){
                return i;
            }
        }
        return -1;
    }
    private Player getNowPlaying(){
        return this.match.getPlayer().peekFirst();
    }
    public Match getMatch(){return match;};
    private void setResponse(){
        response = true;
        notifyAll();
        log.info("Turn went ok");
    }
    private synchronized  void sendMatch(){
        for(ObjectOutputStream objectOutputStream: objectOutputStreams){
            try {
                objectOutputStream.reset();
                objectOutputStream.writeObject(new Message("","server", Content.PICKEDTILE, match.getP(),match.getPlayer()));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public synchronized void handleTurn(int column, Vector<Tiles> tiles, String sender) {
        int index = getThisPlayer(sender);
        if(getNowPlaying().getNickname().equals(sender)){
            int response = match.newTurn(column,tiles);
           if( response == 0){
               setResponse();
               sendMatch();
           }else if(response == 1) {
                log.info("game is ending");
           }else{
               try {
                   objectOutputStreams[index].writeObject(new Message(sender, Content.FAIL));
               }catch (IOException e){
                   e.printStackTrace();
               }
           }

        }else{
            log.warning("Wrong player");
            if(index != -1){
                try {
                    objectOutputStreams[index].writeObject(new Message(sender, Content.FAIL));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else{
                log.severe("ERROR UNKNOWN PLAYER");
            }
        }

    }
}
