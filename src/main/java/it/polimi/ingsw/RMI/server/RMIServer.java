package it.polimi.ingsw.RMI.server;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import it.polimi.ingsw.RMI.*;


public class RMIServer{

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        GameHandlerImplementation gameHandlerImplementation = new GameHandlerImplementation();
        GameHandlerRMI stub = (GameHandlerRMI) UnicastRemoteObject.exportObject(gameHandlerImplementation, 0);

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("GameHandler", stub);

    }

}
