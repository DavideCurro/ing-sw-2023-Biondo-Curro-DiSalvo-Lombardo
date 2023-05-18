package it.polimi.ingsw.Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class StarterServerRMI extends Thread{
    private static final Logger log = Logger.getLogger(StarterServerSocket.class.getName());
    private Registry registry;

    public StarterServerRMI() {}

    public void run(){
        try{
            registry = LocateRegistry.createRegistry(2001);
        }catch (RemoteException e){
            e.printStackTrace();
        }
        while (true) {
            GameHandlerRMI stub = null;
            try {
                stub = (GameHandlerRMI) UnicastRemoteObject.exportObject(new GameHandlerImplementation(registry), 0);
                registry.rebind("GameHandler", stub);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
