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
    static void visualChoose(String arg, Socket socket) throws IOException, InterruptedException {
        switch (arg){
            case "CLI"->{
                cliHandler cliHandler = new cliHandler(socket, new ClientView());
                cliHandler.cliSocket();
            }
            case "GUI"->{
                Application.launch(Launcher.class);
                //cliHandler cliHandler = new cliHandler(socket, new ());
                //cliHandler.cliSocket();
                //Launcher.main(new String[]{arg});
                //Application.launch();
                cliHandler cliHandler = new cliHandler(socket, new GUI());
                cliHandler.guisocket();
                //GUI.main();

            }
        }

    }
    static void visualChoose(String arg, Registry registry) throws IOException, InterruptedException {
        switch (arg){
            case "CLI"->{
               cliHandler cliHandler = new cliHandler(registry,new ClientView());
               cliHandler.cliRMI();
            }
            case "GUI"->{
               // GUI GUI = new GUI(InetAddress.getLocalHost(), 2000);
              //  GUI.main();

            }
        }

    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if(args.length > 0) {
            switch (args[0]) {
                case "RMI" -> {
                    visualChoose(args[1], LocateRegistry.getRegistry("localhost",2001));
                }
                case "Socket" -> {

                    visualChoose(args[1],new Socket(InetAddress.getLocalHost(),2000));
                }
            }
        }
        System.out.println("No args detected, you will be logged into cli");
        cliHandler cliHandler = new cliHandler(new Socket(InetAddress.getLocalHost(),2000), new ClientView());
        cliHandler.cliSocket();

    }
}





