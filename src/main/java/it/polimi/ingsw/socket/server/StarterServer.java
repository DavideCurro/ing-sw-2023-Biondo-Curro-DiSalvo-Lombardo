package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.controller.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class StarterServer {
    public static final int PORT = 2000;
    private final WaitingRoom waitingRoom2Player;
    private final WaitingRoom waitingRoom3Player;
    private final WaitingRoom waitingRoom4Player;
    private static final Logger log = Logger.getLogger(StarterServer.class.getName());

    public StarterServer(){
        waitingRoom2Player = new WaitingRoom(2);
        waitingRoom3Player = new WaitingRoom(3);
        waitingRoom4Player = new WaitingRoom(4);
    }
    public void start() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
        }catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        log.info("Server ready");
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                log.info("New Connection");
                new ServerThread(waitingRoom2Player,waitingRoom3Player,waitingRoom4Player,socket).start();
            } catch (IOException e) {
                break;
            }
        }
        try {
            serverSocket.close();
        }catch (IOException e) {
            System.exit(-1);
        }

    }

}
