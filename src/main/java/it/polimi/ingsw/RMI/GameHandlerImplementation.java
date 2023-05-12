package it.polimi.ingsw.RMI;

import it.polimi.ingsw.Controller.Match;
import it.polimi.ingsw.Controller.MatchExeception;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.rmi.RemoteException;
import java.util.Vector;

public class GameHandlerImplementation implements GameHandlerRMI{
    Match match;
    public GameHandlerImplementation(Match match){
        this.match = match;
    }
    @Override
    public void handleLogin(String nickname, int lobbyType)  throws RemoteException {
        try {
            match.newPlayer(nickname);

        } catch (MatchExeception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int handleTurn(int column, Vector<Tiles> tilesPicked) throws RemoteException {
        return match.newTurn(column,tilesPicked);
    }
    public boolean handleNicknameFail(String nickname) throws RemoteException{
        return false;
    }

    @Override
    public Playground playgroundStart() throws RemoteException {
        match.setupPlayground();
        return match.getP();
    }
}
