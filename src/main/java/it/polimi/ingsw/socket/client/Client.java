package it.polimi.ingsw.socket.client;

import java.io.*;
import java.net.InetAddress;


public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        ServerHandler serverHandler = new ServerHandler(InetAddress.getLocalHost(),2000,new ClientView());
        /*switch (mode){
            case "CLI": serverHandler.cli();
            case "GUI": serverHandler.gui();
        }*/
        serverHandler.cli();

    }
}





