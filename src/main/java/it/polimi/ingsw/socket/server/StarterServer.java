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
    private final Lobby lobby2Player;
    private final Lobby lobby3Player;
    private final Lobby lobby4Player;
    private static final Logger log = Logger.getLogger(StarterServer.class.getName());

    public StarterServer(){
        lobby2Player = new Lobby(2);
        lobby3Player = new Lobby(3);
        lobby4Player = new Lobby(4);
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
                new ServerThread(lobby2Player,lobby3Player,lobby4Player,socket).start();
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
