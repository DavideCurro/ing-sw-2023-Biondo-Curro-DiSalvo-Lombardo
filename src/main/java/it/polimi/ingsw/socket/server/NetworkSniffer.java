package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.socket.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Logger;

import static it.polimi.ingsw.socket.Content.PICKEDTILE;

public class NetworkSniffer extends Thread{
    private ObjectInputStream[] inputStreams;
    private GameHandler game_handler;
    private Socket[] clients;
    private static final Logger log = Logger.getLogger(NetworkSniffer.class.getName());
    private Message received;

    public NetworkSniffer(Socket[] clients, ObjectInputStream[] inputStream,GameHandler game_handler){
        this.clients = clients;
        this.game_handler = game_handler;
        this.inputStreams = inputStream;

    }


    public void run(){
        log.info("Start receiving message");
        while(true){
            try{
                for (ObjectInputStream inputStream : inputStreams) {
                    log.info("Waiting");
                    log.info("Message arrived");
                    synchronized (inputStream) {
                        received =(Message) inputStream.readObject();
                    }
                    System.out.println(received.getSender());
                    game_handler.handleTurn((Integer) received.getPayload(), (Vector<Tiles>) received.getPayload2(), received.getSender()); //column, vector

                    }
            }catch (ClassNotFoundException | IOException exception){
                exception.printStackTrace();

            }
        }
    }
}
