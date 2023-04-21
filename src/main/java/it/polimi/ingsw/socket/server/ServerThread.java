package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.controller.VirtualView;
import it.polimi.ingsw.model.Playground.Tiles;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Vector;

public class ServerThread extends Thread{
    private Socket socket;
    private ArrayList<ServerThread> serverThreadArrayList;
    private GameHandler gameHandler;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Object payload;
    public ServerThread(Socket socket,ArrayList<ServerThread> serverThreadArrayList,GameHandler gameHandler) throws IOException {
        this.serverThreadArrayList = serverThreadArrayList;
        this.socket = socket;
        socket.setSoTimeout(15000);
        this.gameHandler = gameHandler;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }
    @Override
    public void run(){
        firstConnection();
        while(true){
            try {
                pingClient();
                if(payload instanceof Vector<?>){
                    Vector<Tiles> coordinate = (Vector<Tiles>) payload;
                    int column = (int) objectInputStream.readObject();
                    gameHandler.handleNewTurn(column,coordinate);
                }

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void firstConnection(){
        try {
            payload = objectInputStream.readObject();
            String nickname = (String) payload;
            System.out.println("Nickname: " + nickname);
            gameHandler.handleNewPlayer(nickname);
            payload = objectInputStream.readObject();
            int numOfPlayer = (int) payload;
            gameHandler.setNumOfPlayers(numOfPlayer);
            objectOutputStream.writeObject("Successful registered and game started with: "+numOfPlayer);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void pingClient() throws IOException, ClassNotFoundException {
        objectOutputStream.writeObject("PING");
        socket.setSoTimeout(15000);
        payload = objectInputStream.readObject();
        String ack = (String) payload;
        System.out.println("ACK from "+socket.getInetAddress());
        payload =  objectInputStream.readObject();
        socket.setSoTimeout(0);
    }
}

