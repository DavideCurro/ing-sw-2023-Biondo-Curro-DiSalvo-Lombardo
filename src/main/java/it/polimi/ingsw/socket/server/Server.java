package it.polimi.ingsw.socket.server;
import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.controller.MatchExeception;
import it.polimi.ingsw.model.player.Player;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class Server {



    public static void main(String[] args) throws IOException, ClassNotFoundException, MatchExeception {
        ArrayList<ServerThread>serverThreadArrayList = new ArrayList<>();
        try(ServerSocket serverSocket = new ServerSocket(2000)){
            while (true){
                if(serverThreadArrayList.size()<4){
                    Socket socket = serverSocket.accept();
                    ServerThread serverThread = new ServerThread(socket,serverThreadArrayList);
                    serverThreadArrayList.add(serverThread);
                    serverThread.start();
                }
            }
        }catch (Exception e){
            System.exit(-1);
        }

    }
}
