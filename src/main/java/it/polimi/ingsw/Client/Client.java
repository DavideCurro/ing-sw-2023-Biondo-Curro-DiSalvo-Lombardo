package it.polimi.ingsw.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
    public Client(String args) throws IOException, ClassNotFoundException, InterruptedException {
        start(args);
    }
    public Client() throws IOException, ClassNotFoundException, InterruptedException {
        start("");
    }
    public void start(String args) throws IOException, ClassNotFoundException, InterruptedException {
        if(!args.isEmpty()) {
            cliHandler cliHandler = new cliHandler(new Socket(args,2000), new ClientView());
            cliHandler.cliSocket();
        }
        System.out.println("No args detected, you will be logged into localhost");
        cliHandler cliHandler = new cliHandler(new Socket(InetAddress.getLocalHost(),2000), new ClientView());
        cliHandler.cliSocket();

    }
}





