package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.GUI.Controllers.GUI;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;


public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if(args.length > 0) {
            switch (args[0]) {
                case "CLI" -> {
                    cliHandler cliHandler = new cliHandler(InetAddress.getLocalHost(), 2000, new ClientView());
                    cliHandler.cli();
                }
                case "GUI" -> {
                    GUI GUI = new GUI(InetAddress.getLocalHost(), 2000);
                    GUI.start(new Stage());
                    System.out.println("Not already done!");
                }
            }
        }
        System.out.println("No args detected, you will be logged into cli");
        cliHandler cliHandler = new cliHandler(InetAddress.getLocalHost(), 2000, new ClientView());
        cliHandler.cli();

    }
}





