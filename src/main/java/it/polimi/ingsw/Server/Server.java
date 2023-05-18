package it.polimi.ingsw.Server;



import java.rmi.RemoteException;
import java.util.LinkedList;

public class Server {
    /**
     * Hello I start the server
     * @param args ciao
     */
    public static LinkedList<Lobby> lobby2Player = new LinkedList<>();

    static LinkedList<Lobby> lobby3Player = new LinkedList<>();

    static LinkedList<Lobby> lobby4Player = new LinkedList<>();


    public static void main(String[] args) throws RemoteException {
        lobby2Player.add(new Lobby(2));
        lobby3Player.add(new Lobby(3));
        lobby4Player.add(new Lobby(4));
        new StarterServerRMI().start();
        new StarterServerSocket(lobby2Player, lobby3Player, lobby4Player).start();


    }
}
