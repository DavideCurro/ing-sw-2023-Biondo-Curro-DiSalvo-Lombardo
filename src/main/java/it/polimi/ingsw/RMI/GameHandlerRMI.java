package it.polimi.ingsw.RMI;

import it.polimi.ingsw.Model.Playground.Tiles;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface GameHandlerRMI extends Remote {
        boolean handleLogin(String nickname) throws RemoteException;
        boolean handleTurn(int column, Vector<Tiles> tilesPicked)throws RemoteException;
}
