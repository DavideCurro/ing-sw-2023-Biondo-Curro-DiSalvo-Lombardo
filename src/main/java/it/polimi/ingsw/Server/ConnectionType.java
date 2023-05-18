package it.polimi.ingsw.Server;


import java.io.IOException;
import java.net.Socket;
import java.rmi.registry.Registry;

public class ConnectionType {

    private  final int  lobbyCode;
    private GameHandlerImplementation rmiServer;
    private Registry registry;
    private Socket socket;

    public ConnectionType(int lobbyCode){
        this.lobbyCode = lobbyCode;
        rmiServer = null;
        socket = null;
    }

    public void setRMI(GameHandlerImplementation gameHandlerImplementation ,Registry client) {
        this.rmiServer = gameHandlerImplementation ;
        this.registry = client;

    }

    public void setSocket(Socket client) {
        this.socket = client;
    }
    public void close(){
        if(rmiServer==null){
            try{
                socket.close();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }else{

        }
    }
    public void reBind(){
        rmiServer.reBind(String.valueOf(lobbyCode));
    }
    public boolean isSocket(){
        return socket != null;
    }
    public void unLock(){
        rmiServer.unLock();
    }
    public void setGameHandler(GameHandler gameHandler){
        rmiServer.setGameHandler(gameHandler);
    }
}
