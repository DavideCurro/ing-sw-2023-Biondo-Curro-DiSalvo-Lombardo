package it.polimi.ingsw.NotWorking;

import it.polimi.ingsw.Utility.Message.Message;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface GameHandlerRMI extends Remote {
        int handleLogin(String nickname, int lobbyType) throws RemoteException;
    void handleTurn(int column, Vector<Tiles> tilesPicked, String nick) throws RemoteException;

    boolean handleNicknameFail(String nickname) throws  RemoteException;
        Message getData() throws RemoteException;
        boolean isAlive() throws RemoteException;
        int getLobbyType() throws RemoteException;
        String getNickname() throws RemoteException;
}
