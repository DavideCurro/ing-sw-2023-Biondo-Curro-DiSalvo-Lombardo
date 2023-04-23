package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.Match;

import it.polimi.ingsw.controller.VirtualView;
import it.polimi.ingsw.model.player.Player;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class Server {



    public static void main(String[] args){
        ArrayList<ServerThread>serverThreadArrayList = new ArrayList<>();
        GameHandler gameHandler = new GameHandler(new Controller(new Match(),new VirtualView()));
        LinkedList<Player> players = new LinkedList<>();
        try(ServerSocket serverSocket = new ServerSocket(2000)){
            while (true){
                if(serverThreadArrayList.size()<4){
                    Socket socket = serverSocket.accept();
                    ServerThread serverThread = new ServerThread(socket,serverThreadArrayList,gameHandler, players);
                    serverThreadArrayList.add(serverThread);
                    serverThread.start();
                }
            }
        }catch (Exception e){
            System.exit(-1);
        }

    }
}
