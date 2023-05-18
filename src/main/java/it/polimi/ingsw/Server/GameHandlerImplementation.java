package it.polimi.ingsw.Server;

import it.polimi.ingsw.Utility.Message.Content;
import it.polimi.ingsw.Utility.Message.Message;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class GameHandlerImplementation implements GameHandlerRMI{
    private GameHandler gameHandler;
    private boolean lock = true;
    private boolean connected = false;
    private Registry registry;
    public GameHandlerImplementation(Registry registry){
        this.registry = registry;
    }
    public void setGameHandler(GameHandler gameHandler){this.gameHandler = gameHandler;}
    @Override
    public int handleLogin(String nickname, int lobbyType)  throws RemoteException {
        connected = true;
        switch (lobbyType){
            case 2->{
                int newCode = Server.lobby2Player.getLast().getLobbyCode();
                Server.lobby2Player.getLast().connection(registry,null,null,nickname);
                return newCode;
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

    public void reBind(String lobby){
        GameHandlerRMI stub = null;
            try {
                stub = (GameHandlerRMI) UnicastRemoteObject.exportObject(new GameHandlerImplementation(registry), 0);
                registry.bind(lobby,stub );
            }catch (Exception e){
                e.printStackTrace();
            }
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
