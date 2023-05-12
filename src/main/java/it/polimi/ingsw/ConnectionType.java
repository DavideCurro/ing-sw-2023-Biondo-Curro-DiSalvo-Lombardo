package it.polimi.ingsw;

import it.polimi.ingsw.RMI.server.RMIServer;

import java.net.Socket;

public class ConnectionType {
    Socket socket;
    RMIServer rmiServer;

    public ConnectionType(RMIServer rmiServer) {
        this.rmiServer = rmiServer;
        this.socket = null;
    }

    public ConnectionType(Socket socket) {
        this.socket = socket;
        this.rmiServer = null;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public RMIServer getRmiServer() {
        return rmiServer;
    }

    public void setRmiServer(RMIServer rmiServer) {
        this.rmiServer = rmiServer;
    }
}
