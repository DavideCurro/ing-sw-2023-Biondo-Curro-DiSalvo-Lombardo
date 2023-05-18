package it.polimi.ingsw;

import it.polimi.ingsw.RMI.GameHandlerImplementation;
import it.polimi.ingsw.RMI.server.RMIServer;
import it.polimi.ingsw.Socket.Server.GameHandler;

import java.io.IOException;
import java.net.Socket;

public class ConnectionType {

    private  final int  lobbyCode;
    private GameHandlerImplementation rmiServer;
    private Socket socket;

    public ConnectionType(int lobbyCode){
        this.lobbyCode = lobbyCode;
        rmiServer = null;
        socket = null;
    }

    public void setRMI(GameHandlerImplementation client) {
        this.rmiServer = client ;

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
            rmiServer.unbindRegistry();
        }
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
