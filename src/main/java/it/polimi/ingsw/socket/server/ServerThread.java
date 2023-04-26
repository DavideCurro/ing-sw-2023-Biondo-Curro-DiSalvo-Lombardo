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
import java.util.logging.Logger;

import static it.polimi.ingsw.socket.Content.*;

public class ServerThread extends Thread{
    private Socket socket;

    private Lobby lobby2Player;
    private Lobby lobby3Player;
    private Lobby lobby4Player;


    private static final Logger log = Logger.getLogger(ServerThread.class.getName());
    public ServerThread( Lobby waitingRoom2Player, Lobby waitingRoom3Player, Lobby waitingRoom4Player, Socket socket)  {

        this.socket = socket;
        this.lobby2Player = waitingRoom2Player;
        this.lobby3Player = waitingRoom3Player;
        this.lobby4Player = waitingRoom4Player;
    }
    private void connect(Lobby lobby, String username, ObjectOutputStream outputStream, ObjectInputStream inputStream){
        log.info("Doing stuff for log in waiting room");
        lobby.connection(socket, outputStream, inputStream, username);


    }
    @Override
    public void run(){
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try{
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
            outputStream.reset();
            inputStream = new ObjectInputStream(socket.getInputStream());
        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        Message message = null;
            try {
                synchronized (inputStream) {
                    message = (Message) inputStream.readObject();
                }
                switch ((int)message.getPayload()){
                    case 2->{
                        log.info("Connecting in 2 player room");
                        connect(lobby2Player,message.getSender(),outputStream,inputStream);

                    }
                    case 3->{
                        log.info("Connecting in 3 player room");
                        connect(lobby3Player,message.getSender(),outputStream,inputStream);

                    }
                    case 4->{
                        log.info("Connecting in 4 player room");
                        connect(lobby4Player,message.getSender(),outputStream,inputStream);
                    }
                    default->{
                        log.info("ERROR wrong number");
                        socket.close();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }



