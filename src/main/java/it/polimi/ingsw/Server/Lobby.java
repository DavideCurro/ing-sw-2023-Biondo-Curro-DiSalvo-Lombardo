package it.polimi.ingsw.Server;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Lobby {
    private final Socket[] clients;
    private final String[] usernames;
    private final ObjectInputStream[] objectInputStreams;
    private final ObjectOutputStream[] objectOutputStreams;
    private int index;
    private static final Logger log = Logger.getLogger(Lobby.class.getName());
    private final ExecutorService launcher; //needs to exec the gamehandler
    private final int lobbyCode;
    private ConnectionType[] connectionType;

    /**
     * It's a constructor
     * @param nPlayer , player for the lobby
     */
    public Lobby(int nPlayer){
        log.info("New Room created the capacity is" + nPlayer);
        clients = new Socket[nPlayer];
        launcher = Executors.newCachedThreadPool();
        objectInputStreams = new ObjectInputStream[nPlayer];
        objectOutputStreams = new ObjectOutputStream[nPlayer];
        usernames = new String[nPlayer];
        index = 0;
        Random random = new Random();
        lobbyCode =random.nextInt(99999);
        connectionType = new ConnectionType[nPlayer];
    }

    /**
     * is full? test the max connection by getting nPlayer from constructor and how many people logged
     * @return true if is full
     */
    public boolean isFull(){
        return connectionType.length == index;
    }

    /**
     * How many people can connect?
     * @return max number of players
     */
    public int maxConnection(){
        return connectionType.length;
    }

    /**
     * Connect a player to the lobby, if the lobby is full the game will start
     * @param client Socket
     * @param outputStream ObjectOutputStream
     * @param inputStream ObjectInputStream
     * @param username String
     */
    public synchronized void connection(Socket client, ObjectOutputStream outputStream, ObjectInputStream inputStream, String username){
        connectionType[index] = new ConnectionType(lobbyCode);
        connectionType[index].setSocket(client);
        objectOutputStreams[index] = outputStream;
        objectInputStreams[index] = inputStream;
        usernames[index] = username;
        index++;
        log.info("New player connected "+ client.toString());
        if(connectionType.length == index){
            log.info("Start game");
            launcher.submit(new GameHandler(connectionType,usernames,objectOutputStreams,objectInputStreams));
        }

    }
    public synchronized void connection(Registry client, ObjectOutputStream outputStream, ObjectInputStream inputStream, String username){
        connectionType[index] = new ConnectionType(lobbyCode);
        connectionType[index].setRMI(new GameHandlerImplementation(client),client);
        connectionType[index].reBind();
       // connectionType[index].setRMI(client);
        objectOutputStreams[index] = null;
        objectInputStreams[index] = null;
        usernames[index] = username;
        index++;
        log.info("New player connected "+ client.toString());
        if(connectionType.length == index){
            log.info("Start game");
            launcher.submit(new GameHandler(connectionType,usernames,objectOutputStreams,objectInputStreams));
        }

    }
    public int getLobbyCode(){return lobbyCode;}
}
