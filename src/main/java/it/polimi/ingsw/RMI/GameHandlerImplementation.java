package it.polimi.ingsw.RMI;

import it.polimi.ingsw.Controller.Match;
import it.polimi.ingsw.Message.Content;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Socket.Server.GameHandler;
import it.polimi.ingsw.Socket.Server.Server;

import java.lang.reflect.Member;
import java.rmi.RemoteException;
import java.util.Vector;

public class GameHandlerImplementation implements GameHandlerRMI{
    private GameHandler gameHandler;
    private boolean lock = true;
    private boolean connected = false;
    public GameHandlerImplementation(){ }
    public void setGameHandler(GameHandler gameHandler){this.gameHandler = gameHandler;}
    @Override
    public void handleLogin(String nickname, int lobbyType)  throws RemoteException {
        connected = true;
        switch (lobbyType){
            case 2->{
                Server.lobby2Player.getLast().connection(this,null,null,nickname);
            }
            default -> throw new IllegalStateException("Unexpected value: " + lobbyType);
        }

    }

    @Override
    public int handleTurn(int column, Vector<Tiles> tilesPicked) throws RemoteException {
        return 1;
    }
    public boolean handleNicknameFail(String nickname) throws RemoteException{
        return false;
    }

    public void unbindRegistry(){

    }
    public void unLock(){
        lock = false;
    }
    public Message getData() throws RemoteException {
        if(lock)
            return new Message("Client", Content.FAIL);
        return new Message("Client", Content.NEWGAME, gameHandler.getMatch().getP());
    }
    public boolean getConnected(){return connected;}
    public void setConnected(boolean connected){
        this.connected = connected;
    }
}
