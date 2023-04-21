package it.polimi.ingsw.socket.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private Socket socket;
    private ArrayList<ServerThread> serverThreadArrayList;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    public ServerThread(Socket socket,ArrayList<ServerThread> serverThreadArrayList) throws IOException {
        this.serverThreadArrayList = serverThreadArrayList;
        this.socket = socket;
        socket.setSoTimeout(15000);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }
    @Override
    public void run(){
        String message = null;
        try {
            message = (String) objectInputStream.readObject();
            System.out.println("Nickname: " + message);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(true){
            try {
                objectOutputStream.writeObject("Successful registered");
                objectOutputStream.writeObject("PING");
                message = (String) objectInputStream.readObject();
                System.out.println(message);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

