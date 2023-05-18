package it.polimi.ingsw.RMI.server;


import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.Controller.Match;
import it.polimi.ingsw.RMI.*;


public class RMIServer{
    private GameHandlerImplementation gameHandlerImplementation;
    public RMIServer(GameHandlerImplementation gameHandlerImplementation) throws RemoteException {
        this.gameHandlerImplementation = gameHandlerImplementation;
        GameHandlerRMI stub = (GameHandlerRMI) UnicastRemoteObject.exportObject(gameHandlerImplementation, 0);
        Registry registry = LocateRegistry.createRegistry(2001);
        try {
            registry.bind("GameHandler", stub);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public GameHandlerImplementation getGameHandlerImplementation(){
        return gameHandlerImplementation;
    }

}
