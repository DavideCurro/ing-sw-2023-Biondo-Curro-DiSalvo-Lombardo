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

    private WaitingRoom waitingRoom2Player;
    private WaitingRoom waitingRoom3Player;
    private WaitingRoom waitingRoom4Player;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private static final Logger log = Logger.getLogger(ServerThread.class.getName());
    public ServerThread( WaitingRoom waitingRoom2Player, WaitingRoom waitingRoom3Player, WaitingRoom waitingRoom4Player, Socket socket)  {

        this.socket = socket;
        this.waitingRoom2Player = waitingRoom2Player;
        this.waitingRoom3Player = waitingRoom3Player;
        this.waitingRoom4Player = waitingRoom4Player;
        try {
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
            outputStream.reset();
        }catch (IOException e){
            log.info("Can't create serverThread");
        }
    }
    private void connect(WaitingRoom waitingRoom, String username){
        log.info("Doing stuff for log in waiting room");
        waitingRoom.connection(socket, outputStream, inputStream, username);


    }
    @Override
    public void run(){
        Message message = null;
        while(socket.isConnected()){
            try {
                synchronized (inputStream) {
                    message = (Message) inputStream.readObject();
                }
                switch ((int)message.getPayload()){
                    case 2->{
                        log.info("Connecting in 2 player room");
                        connect(waitingRoom2Player,message.getSender());

                    }
                    case 3->{
                        log.info("Connecting in 3 player room");
                        connect(waitingRoom3Player,message.getSender());

                    }
                    case 4->{
                        log.info("Connecting in 4 player room");
                        connect(waitingRoom4Player,message.getSender());
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

    }


