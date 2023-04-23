package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.socket.Content;
import it.polimi.ingsw.socket.Message;
import it.polimi.ingsw.socket.server.GameHandler;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import static it.polimi.ingsw.socket.Content.*;

public class ServerThread extends Thread{
    private Socket socket;
    private ArrayList<ServerThread> serverThreadArrayList;
    private GameHandler gameHandler;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private LinkedList<Player> players;
    public ServerThread(Socket socket, ArrayList<ServerThread> serverThreadArrayList, GameHandler gameHandler, LinkedList<Player> players) throws IOException {
        this.serverThreadArrayList = serverThreadArrayList;
        this.socket = socket;
        socket.setSoTimeout(0);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.gameHandler = gameHandler;
        this.players = players;
    }
    @Override
    public void run(){
        Message message = null;
        while(socket.isConnected()){
            try {
                message = (Message) objectInputStream.readObject();
                handleNewMessage(message);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void handleNewMessage(Message message) throws IOException, ClassNotFoundException {
        switch (message.getMessageType()){
            case ACK ->{
                socket.setSoTimeout(0);
                System.out.println(socket.toString()+ " is Alive, handling NEW turn");
               // handleNewTurn();
            }
            case NICKNAME -> {
                gameHandler.handleNewPlayer((String) message.getPayload());
                players.add(new Player(message.getSender()));
                objectOutputStream.writeObject(new Message(message.getSender(),Content.GAMEJOIN,this.gameHandler.getController().getMatch().getPlayer().getLast()));
                if(serverThreadArrayList.size() == 1){
                    objectOutputStream.writeObject(new Message(message.getSender(), NEWGAME,"Choose number of player"));
                }else{
                    objectOutputStream.writeObject(new Message(message.getSender(),GAMECREATED,this.gameHandler.getController().getMatch().getP()));
                }

            }
            case NEWGAME -> {
                gameHandler.setNumOfPlayers((int)message.getPayload());
                gameHandler.handleStartGame();
                objectOutputStream.writeObject(new Message(message.getSender(),GAMECREATED,this.gameHandler.getController().getMatch().getP()));
            }

        }
    }

    public void ping() throws IOException {
        objectOutputStream.writeObject(new Message("player",NEWTURN));
        socket.setSoTimeout(15000);
    }
}

