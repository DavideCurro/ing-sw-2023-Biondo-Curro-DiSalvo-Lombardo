package it.polimi.ingsw.RMI;

import it.polimi.ingsw.model.Playground.Tiles;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class RmiStarter extends UnicastRemoteObject implements GameHandlerRMI {
    protected RmiStarter() throws RemoteException {
    }

    @Override
    public boolean handleLogin(String nickname) {
        return false;
    }

    @Override
    public boolean handleTurn(int column, Vector<Tiles> tilesPicked) {
        return false;
    }
}
