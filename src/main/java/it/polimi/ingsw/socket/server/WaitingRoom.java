package it.polimi.ingsw.socket.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class WaitingRoom {
    private Socket[] clients;
    private String[] usernames;
    private ObjectInputStream[] objectInputStreams;
    private ObjectOutputStream[] objectOutputStreams;
    private int index;
    private static final Logger log = Logger.getLogger(WaitingRoom.class.getName());
    private final ExecutorService launcher;

    public WaitingRoom(int nPlayer){
        log.info("New Room created the capacity is" + nPlayer);
        clients = new Socket[nPlayer];
        launcher = Executors.newCachedThreadPool();
        objectInputStreams = new ObjectInputStream[nPlayer];
        objectOutputStreams = new ObjectOutputStream[nPlayer];
        usernames = new String[nPlayer];
        index = 0;
    }
    public int getNumConnection(){
        return  index;
    }
    public int maxConnection(){
        return clients.length;
    }
    public synchronized void connection(Socket client, ObjectOutputStream outputStream, ObjectInputStream inputStream, String username){
        clients[index] = client;
        objectOutputStreams[index] = outputStream;
        objectInputStreams[index] = inputStream;
        usernames[index] = username;
        index++;
        log.info("New player connected "+ client.toString());
        if(clients.length == index){
            log.info("Start game");
            launcher.submit(new GameHandler(clients,usernames,objectOutputStreams,objectInputStreams));
        }

    }
}
