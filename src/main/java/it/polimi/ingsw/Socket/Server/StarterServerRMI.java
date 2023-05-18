package it.polimi.ingsw.Socket.Server;

import it.polimi.ingsw.RMI.GameHandlerImplementation;
import it.polimi.ingsw.RMI.server.RMIServer;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class StarterServerRMI {
    private static final Logger log = Logger.getLogger(StarterServerSocket.class.getName());
    private RMIServer rmiServer;

    public StarterServerRMI(RMIServer rmiServer) {
        this.rmiServer = rmiServer;
    }
    public void start(){
        if(rmiServer.getGameHandlerImplementation().getConnected()){
            try {
                rmiServer = new RMIServer(new GameHandlerImplementation());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
