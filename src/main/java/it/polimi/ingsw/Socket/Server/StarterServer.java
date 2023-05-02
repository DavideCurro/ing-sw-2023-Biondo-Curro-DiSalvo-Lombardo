package it.polimi.ingsw.Socket.Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import java.util.logging.Logger;

public class StarterServer {
    public static final int PORT = 2000;
    private final LinkedList<Lobby> lobby2Player;
    private final LinkedList<Lobby> lobby3Player;
    private final LinkedList<Lobby> lobby4Player;
    private static final Logger log = Logger.getLogger(StarterServer.class.getName());

    /**
     * It's me a constructor, not Mario
     */
    public StarterServer(){
        lobby2Player = new LinkedList<>();
        lobby2Player.add(new Lobby(2));
        lobby3Player = new LinkedList<>();
        lobby3Player.add(new Lobby(3));
        lobby4Player = new LinkedList<>();
        lobby4Player.add(new Lobby(4));
    }

    /**
     * This method handle new connection by passing it to dispatcher
     */
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
