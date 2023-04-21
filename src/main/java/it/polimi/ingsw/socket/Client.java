package it.polimi.ingsw.socket;

import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.controller.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;


public class Client {



    public static void main(String[] args) throws IOException, ClassNotFoundException {
            //get the localhost IP address, if server is running on some other IP, you need to use that
            InetAddress host = InetAddress.getLocalHost();
            Socket socket =  new Socket(host.getHostName(), 2000);;
            ObjectOutputStream objectOutputStream  = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream =  new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject("Claudio");             //read the server response message
            String message = (String) objectInputStream.readObject();
            System.out.println("Message: " + message);
            if(objectInputStream.readObject() instanceof String){
                System.out.println("Ping from: "+socket.getInetAddress().toString());
                objectOutputStream.writeObject("CIAO");
            }

    }



}

