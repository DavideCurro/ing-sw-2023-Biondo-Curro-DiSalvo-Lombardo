package it.polimi.ingsw.Server;

import it.polimi.ingsw.Utility.Message.Message;

import java.io.*;
import java.net.Socket;

import java.rmi.registry.Registry;
import java.util.LinkedList;

import java.util.Objects;
import java.util.logging.Logger;



public class ServerThread extends Thread{
    private final Socket socket;
    private final GameHandlerRMI stub;
    private static LinkedList<Lobby> lobby2Player;
    private static LinkedList<Lobby> lobby3Player;
    private static LinkedList<Lobby> lobby4Player;
    private Registry registry;

    private static final Logger log = Logger.getLogger(ServerThread.class.getName());

    /**
     * It's me a constructor
     * @param lobby2Player a list of lobby for 2
     * @param lobby3Player a list of lobby for 3
     * @param lobby4Player a list of lobby for 4
     * @param socket Socket
     */
    public ServerThread( LinkedList<Lobby> lobby2Player, LinkedList<Lobby> lobby3Player, LinkedList<Lobby> lobby4Player, Socket socket)  {

        this.socket = socket;
        stub = null;
        registry = null;
        ServerThread.lobby2Player = lobby2Player;
        ServerThread.lobby3Player = lobby3Player;
        ServerThread.lobby4Player = lobby4Player;
    }
    public ServerThread( LinkedList<Lobby> lobby2Player, LinkedList<Lobby> lobby3Player, LinkedList<Lobby> lobby4Player, GameHandlerRMI stub, Registry registry)  {

        this.stub = stub;
        socket = null;
        ServerThread.lobby2Player = lobby2Player;
        ServerThread.lobby3Player = lobby3Player;
        ServerThread.lobby4Player = lobby4Player;
        this.registry = registry;
    }

    /**
     * This method connect a new Player to an existing lobby, in case he is the last player also create a new one for the next player
     * @param lobbyList is the list of lobby of the chosen type of game
     * @param username nickname of player
     * @param outputStream output stream of player
     * @param inputStream input stream of player
     */

    private void connect(LinkedList<Lobby> lobbyList, String username, ObjectOutputStream outputStream, ObjectInputStream inputStream){
        log.info("Doing stuff for log in waiting room");
        Lobby lobby1 = lobbyList.getLast();
        lobby1.connection(socket,outputStream,inputStream,username);
        if(lobby1.isFull())
            lobbyList.add(new Lobby(lobby1.maxConnection()));
    }
    public int rmiConnection(LinkedList<Lobby> lobbyLinkedList, Registry registry, String username){
        log.info("Doing stuff for log in waiting room");
        Lobby lobby1 = lobbyLinkedList.getLast();
        lobby1.connection(registry,username);
        if(lobby1.isFull())
            lobbyLinkedList.add(new Lobby(lobby1.maxConnection()));
        return lobby1.getLobbyCode();
    }

    /**
     * This method handle the lobby choose, dispatch new player in a lobby
     */
    @Override
    public void run() {
        if (Objects.isNull(socket)) {
            rmi();
        } else {
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;
            try {
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.flush();
                outputStream.reset();
                inputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }

            Message message;
            try {
                message = (Message) inputStream.readObject();
                switch ((int) message.getPayload()) {
                    case 2 -> {
                        log.info("Connecting in 2 player room");
                        connect(lobby2Player, message.getSender(), outputStream, inputStream);

                    }
                    case 3 -> {
                        log.info("Connecting in 3 player room");
                        connect(lobby3Player, message.getSender(), outputStream, inputStream);

                    }
                    case 4 -> {
                        log.info("Connecting in 4 player room");
                        connect(lobby4Player, message.getSender(), outputStream, inputStream);
                    }
                    default -> {
                        log.info("ERROR wrong number");
                        socket.close();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void rmi(){
        try {
            switch (stub.getLobbyType()) {
                case 2 -> {
                    log.info("Connecting in 2 player room");
                    rmiConnection(lobby2Player,registry, stub.getNickname());

                }
                case 3 -> {
                    log.info("Connecting in 3 player room");
                    rmiConnection(lobby3Player,registry, stub.getNickname());

                }
                case 4 -> {
                    log.info("Connecting in 4 player room");
                    rmiConnection(lobby4Player,registry, stub.getNickname());
                }
                default -> {
                    log.info("ERROR wrong number");
                    sleep(300000);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}



