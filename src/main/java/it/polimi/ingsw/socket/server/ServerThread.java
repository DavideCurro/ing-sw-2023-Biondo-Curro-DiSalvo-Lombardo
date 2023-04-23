package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.socket.Content;
import it.polimi.ingsw.socket.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Vector;

import static it.polimi.ingsw.socket.Content.*;

public class ServerThread extends Thread{
    private Socket socket;
    private LinkedList<ServerThread> serverThreadArrayList;
    private GameHandler gameHandler;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private LinkedList<Player> players;
    private int column;
    private Vector<Tiles> tilesVector;
    public ServerThread(Socket socket, LinkedList<ServerThread> serverThreadArrayList, GameHandler gameHandler, LinkedList<Player> players) throws IOException {
        this.serverThreadArrayList = serverThreadArrayList;
        this.socket = socket;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.gameHandler = gameHandler;
        this.players = players;
        column = -1;
        tilesVector = new Vector<>();
    }
    @Override
    public void run(){
        Message message = null;
        while(socket.isConnected()){
            try {
                message = (Message) objectInputStream.readObject();
                handleNewMessage(message);
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void handleNewMessage(Message message) throws IOException, ClassNotFoundException, InterruptedException {
        switch (message.getMessageType()){
            case ACK ->{
                socket.setSoTimeout(0);
                System.out.println(socket.toString()+ " is Alive, handling NEW turn");
                objectOutputStream.writeObject(new Message(message.getSender(),PICKTILES,""));
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
                while(serverThreadArrayList.size() != this.gameHandler.getController().getNumPlayer()) sleep(50);
                socket.setSoTimeout(15000);
                objectOutputStream.writeObject(new Message(message.getSender(),PING,""));
            }
            case TILESPICKED -> {
                tilesVector = (Vector<Tiles>) message.getPayload();
                while (serverThreadArrayList.size() != gameHandler.getController().getNumPlayer()) sleep(50);
                gameHandler.handleNewTurn(column,tilesVector);
                Player tmp = gameHandler.getController().getMatch().getLastPlayer();
                gameHandler.getController().getView().printPlayerLibrary(tmp);
                gameHandler.getController().getView().printPlayground(gameHandler.getController().getMatch().getP());
                objectOutputStream.reset();
                objectOutputStream.writeObject(new Message(message.getSender(), SUCCESS ,tmp));
                objectOutputStream.writeObject(new Message(message.getSender(), PLAYGROUND, gameHandler.getController().getMatch().getP()));
                ServerThread alreadyPlayed = serverThreadArrayList.pollFirst();
                serverThreadArrayList.addLast(alreadyPlayed);
                serverThreadArrayList.peekFirst().ping();
                Message tmpMessage;
                while (true){
                    tmpMessage = (Message) objectInputStream.readObject();
                    if(tmpMessage.getMessageType() == PING) break;
                    sleep(2000);
                }
            }
            case COORDINATE -> {
                column = (int)message.getPayload();
            }

        }
    }

    public void ping() throws IOException {
        objectOutputStream.writeObject(new Message("player",PING));
        socket.setSoTimeout(15000);
    }

}

