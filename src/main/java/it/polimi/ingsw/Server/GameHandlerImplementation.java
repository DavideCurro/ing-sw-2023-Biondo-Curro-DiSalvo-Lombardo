package it.polimi.ingsw.Server;

import it.polimi.ingsw.Utility.Message.Content;
import it.polimi.ingsw.Utility.Message.Message;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Vector;

public class GameHandlerImplementation implements GameHandlerRMI{
    private GameHandler gameHandler = null;
    private boolean lock = true;
    private boolean connected = false;
    private Registry registry;
    private boolean startGame = false;
    private String nickname;
    private int lobbyType;
    LinkedList<Message> startMessage = new LinkedList<>();
    private boolean alive =true;
    public GameHandlerImplementation(Registry registry){
        this.registry = registry;
    }
    public void setGameHandler(GameHandler gameHandler){this.gameHandler = gameHandler;}
    @Override
    public int handleLogin(String nickname, int lobbyType)  throws RemoteException {
        this.nickname = nickname;
        this.lobbyType = lobbyType;
        return 0;
    }

    public String getNickname() throws RemoteException{
        return nickname;
    }
    public int getLobbyType()throws RemoteException {
        return lobbyType;
    }


    @Override
    public void handleTurn(int column, Vector<Tiles> tilesPicked, String nick) throws RemoteException {
        gameHandler.handleTurn(column,tilesPicked,nick);
    }
    public boolean handleNicknameFail(String nickname) throws RemoteException{
        return false;
    }

    public void reBind(String lobby){
        GameHandlerRMI stub = null;
            try {
                stub = (GameHandlerRMI) UnicastRemoteObject.exportObject(new GameHandlerImplementation(registry), 0);
                registry.rebind(lobby,stub );
            }catch (Exception e){
                e.printStackTrace();
            }
    }
    public void unLock(){
        lock = false;
    }

    public Message getData() throws RemoteException {
        System.out.println(lock);
        if(Objects.isNull(gameHandler))
            return new Message("Cl", Content.FAIL);
        if(startGame){
            startGame = false;
            System.out.println("Ciao1");
            return new Message("Client", Content.StartGame,startMessage);
        }
        System.out.println("Ciao2");
        return gameHandler.getLastMessage();
    }
    public boolean getConnected(){return connected;}
    public void setConnected(boolean connected){
        this.connected = connected;
    }
    public void setAlive(boolean alive){this.alive = alive;}
    public boolean isAlive() throws RemoteException{
        return alive;
    }
    public void setStartGame(Message m1,Message m2,Message m3){
        startGame = true;
        startMessage.add(m1);
        startMessage.add(m2);
        startMessage.add(m3);
    }
}
