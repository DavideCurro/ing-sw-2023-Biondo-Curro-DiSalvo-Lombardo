package it.polimi.ingsw.RMI;

import it.polimi.ingsw.Controller.Match;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface GameHandlerRMI extends Remote {
        void handleLogin(String nickname, int lobbyType) throws RemoteException;
        int handleTurn(int column, Vector<Tiles> tilesPicked)throws RemoteException;
        boolean handleNicknameFail(String nickname) throws  RemoteException;
        Message getData() throws RemoteException;
}
