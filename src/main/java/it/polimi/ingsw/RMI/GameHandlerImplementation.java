package it.polimi.ingsw.RMI;

import it.polimi.ingsw.model.Playground.Tiles;

import java.rmi.RemoteException;
import java.util.Vector;

public class GameHandlerImplementation implements GameHandlerRMI{
    @Override
    public boolean handleLogin(String nickname)  throws RemoteException {
        return false;
    }

    @Override
    public boolean handleTurn(int column, Vector<Tiles> tilesPicked) throws RemoteException {
        return false;
    }
}
