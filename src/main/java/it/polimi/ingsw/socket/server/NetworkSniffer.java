package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.socket.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


import java.util.Vector;
import java.util.logging.Logger;



public class NetworkSniffer extends Thread{
    private ObjectInputStream[] inputStreams;
    private GameHandler game_handler;

    private static final Logger log = Logger.getLogger(NetworkSniffer.class.getName());
    private Message received;

    /**
     * It's a constructor
     * @param inputStream ObjectInputStream[], because we sniff multiple socket
     * @param game_handler sort of a controller
     */
    public NetworkSniffer( ObjectInputStream[] inputStream,GameHandler game_handler){

        this.game_handler = game_handler;
        this.inputStreams = inputStream;

    }

    /**
     * This method look in all socket to retrieve some message that has to be passed to game handler
     */
    public void run(){
        log.info("Start receiving message");
        while(true){
            try{
                for (ObjectInputStream inputStream : inputStreams) {
                    log.info("Waiting");
                   // System.out.println(inputStream.available());
                    if (inputStream.available() > 0){
                        inputStream.readInt();
                        received = (Message) inputStream.readObject();
                        log.info("Message arrived");
                        System.out.println(received.getSender());
                        game_handler.handleTurn((Integer) received.getPayload(), (Vector<Tiles>) received.getPayload2(), received.getSender()); //column, vector
                    }
                    sleep(50);
                }
            }catch (ClassNotFoundException | IOException | InterruptedException exception){
                exception.printStackTrace();
            }
        }
    }
}
