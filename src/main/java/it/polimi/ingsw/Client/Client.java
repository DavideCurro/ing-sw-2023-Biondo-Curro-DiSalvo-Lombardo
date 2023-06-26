package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.GUI.Controllers.GUI;
import it.polimi.ingsw.Client.GUI.Controllers.Launcher;
import javafx.application.Application;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if(args.length > 0) {
            cliHandler cliHandler = new cliHandler(new Socket(args[0],2000), new ClientView());
        }
        System.out.println("No args detected, you will be logged into localhost");
        cliHandler cliHandler = new cliHandler(new Socket(InetAddress.getLocalHost(),2000), new ClientView());
        cliHandler.cliSocket();

    }
}





