package it.polimi.ingsw.socket.client;


import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.socket.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

import static it.polimi.ingsw.socket.Content.*;
import static java.lang.Thread.sleep;

public class ServerHandler {

    private Socket socket;
    private ClientView view;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Vector<Tiles> tilesVector;
    private Scanner scanner;
    private String nickname;

    public ServerHandler(InetAddress host, int port, ClientView view) throws IOException {
        socket =  new Socket(host.getHostName(), port);
        socket.setSoTimeout(0);
        this.view = view;
        objectOutputStream  = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream  = new ObjectInputStream(socket.getInputStream());
        tilesVector = new Vector<>();
        scanner = new Scanner(System.in);
        nickname = "";
    }
    public void cli() throws InterruptedException {
        view.welcome();
        System.out.print("Choose your nickname: ");
        nickname = scanner.nextLine();
        System.out.println("Welcome! "+ nickname + " please choose in which lobby do you want to join!");
        System.out.println("2\t3\t4");
        System.out.print("Please log me in the lobby with : ");
        int lobbyType = -1;
        do {
           lobbyType = scanner.nextInt();
        }while (lobbyType <2 || lobbyType >4);
        System.out.println("You're joining the lobby...");
        try {
            objectOutputStream.writeObject(new Message("Server",nickname, NICKNAME,lobbyType));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (socket.isConnected()) {
            Message message;
            try {
                message = (Message) objectInputStream.readObject();
                handleNewMessage(message);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        sleep(5000);
    }

    private void handleNewMessage(Message message) throws IOException {
        switch (message.getMessageType()){
            case NEWGAME ->
                view.printPlayground((Playground)message.getPayload());

            case PLAYERDATA -> {
                Player tmp = (Player) message.getPayload();
                System.out.println("Your personal goal is: \n");
                view.printPersonalOBJ(tmp);
            }
            case PICKTILE ->{
                objectOutputStream.flush();
                objectOutputStream.reset();
                objectOutputStream.writeObject(new Message("server",nickname,PICKEDTILE,getColumn(), getTilesVector()));
                tilesVector.clear();
            }
            case PICKEDTILE -> {
                Playground playground = (Playground) message.getPayload();
                LinkedList<Player> vectorLinkedList = (LinkedList<Player>) message.getPayload2();
               // view.printPlayerLibrary(match.getLastPlayer());
                view.printPlayground(playground);
                for(Player player : vectorLinkedList){
                    System.out.println(player.getNickname()+"'s library");
                    view.printPlayerLibrary(player);
                    view.printOutPointsPerPlayer(player);
                }
            }
            case NICKNAME_DUPLICATE -> {
                System.out.println("This nickname was already taken. Choose another one: \n");
                scanner = new Scanner(System.in);
                nickname = scanner.nextLine();
                try {
                    objectOutputStream.writeObject(new Message("Server",nickname, NICKNAME,nickname));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case COMMONOBJDONE ->{
                System.out.println("You completed the common goal!");
                Player player = (Player) message.getPayload();
                int countable = (int) message.getPayload2();
                view.printOutPointsPerPlayer(player);
                System.out.println("You are the number "+countable+"player to complete the common goal");
                view.printNewHighestScore(countable);
            }
            case PERSONALOBJDONE -> {
                System.out.println("You completed the personal goal!");
                Player player = (Player) message.getPayload();
                view.printOutPointsPerPlayer(player);
            }
            case PICKUPFAIL -> {
                System.out.println("SOMETHING WENT WRONG WITH YOUR CHOOSE");
                System.out.println("Pick up again your tiles:");
                objectOutputStream.flush();
                objectOutputStream.reset();
                objectOutputStream.writeObject(new Message("server",nickname,PICKEDTILE,getColumn(), getTilesVector()));
                tilesVector.clear();
            }
            case WRONG_PLAYER,FAIL -> {
                System.out.println("Some big unexpected and impossible error occur. You are going to be disconnected from the server");
            }
            case COMMONOBJ -> {
                view.printCommonOBJ(message.getPayload());
            }
        }
    }
    private int validateInput(){
        int x=-1;
            try{
                x = scanner.nextInt()-1;
            }catch (InputMismatchException e){
                e.printStackTrace();
            }
        return x;
    }
    private int getColumn(){
        System.out.println("Column of the library: ");
        return validateInput();
    }
    private Vector<Tiles> getTilesVector(){
        Vector<Tiles> tmp = new Vector<>();
        System.out.println("How many tiles do you want?");
        int len = scanner.nextInt();
        for(int i = 0; i<len;i++){
            System.out.print("Choose the "+(i+1)+" X: ");
            int x = validateInput();
            System.out.print("Choose the "+(i+1)+" Y: ");
            int y = validateInput();
            tmp.add(new Tiles(-1,x,y));
        }
        return tmp;
    }
}
