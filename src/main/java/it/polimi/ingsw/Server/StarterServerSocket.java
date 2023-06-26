package it.polimi.ingsw.Server;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import java.util.logging.Logger;

public class StarterServerSocket extends Thread {
    public static final int PORT = 2000;
    private final LinkedList<Lobby> lobby2Player;
    private final LinkedList<Lobby> lobby3Player;
    private final LinkedList<Lobby> lobby4Player;
    private static final Logger log = Logger.getLogger(StarterServerSocket.class.getName());

    /**
     * It's me a constructor, not Mario
     */
    public StarterServerSocket(){
        lobby2Player = new LinkedList<>();
        lobby2Player.add(new Lobby(2));
        lobby3Player = new LinkedList<>();
        lobby3Player.add(new Lobby(3));
        lobby4Player = new LinkedList<>();
        lobby4Player.add(new Lobby(4));
    }

    public StarterServerSocket(LinkedList<Lobby> lobby2Player, LinkedList<Lobby> lobby3Player, LinkedList<Lobby> lobby4Player) {
        this.lobby2Player = lobby2Player;
        this.lobby3Player = lobby3Player;
        this.lobby4Player = lobby4Player;
    }

    /**
     * This method handle new connection by passing it to dispatcher
     */
    public void start() {
        ServerSocket serverSocket;
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
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
