package it.polimi.ingsw.Client;


import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

import static it.polimi.ingsw.Message.Content.*;
import static java.lang.Thread.sleep;

public class ServerHandler {

    private final Socket socket;
    private final ClientView view;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;
    private final Vector<Tiles> tilesVector;
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
        int gamestart = 0;
        view.welcome();
        System.out.print("Choose your nickname: ");
        nickname = scanner.nextLine();
        System.out.println("Welcome! "+ nickname + " please choose in which lobby do you want to join!");
        System.out.println("2\t3\t4");
        System.out.print("Please log me in the lobby with : ");
        int lobbyType = -1;
        boolean wentToCatch;
        boolean firstIteration = false;
        do {
            if((lobbyType<2 || lobbyType >4)&&(firstIteration)){
                System.out.println("Please choose in which lobby do you want to join!");
                System.out.println("2\t3\t4");
                System.out.print("Please log me in the lobby with : ");
            }
            do{
                try{
                    wentToCatch = false;
                    lobbyType = scanner.nextInt();
                } catch (InputMismatchException e){
                    scanner.next();
                    wentToCatch = true;
                    System.out.println("Re-enter the value!");
                    System.out.println(nickname + " please choose in which lobby do you want to join!");
                    System.out.println("2\t3\t4");
                    System.out.print("Please log me in the lobby with : ");
                }
            }while(wentToCatch);
            firstIteration=true;
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
                if(gamestart == 0)
                    System.out.println("The game has began");
                gamestart++;
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
                objectOutputStream.writeInt(0);
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
                    if(player.getNickname().equals(nickname)){
                        player.setPoints(0);
                        view.printPersonalPoint(player);
                    }else {
                        view.printOutPointsPerPlayer(player);
                    }
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
                player.setPoints(0);
                view.printPersonalPoint(player);
            }
            case PICKUPFAIL -> {
                System.out.println("SOMETHING WENT WRONG WITH YOUR CHOOSE");
                System.out.println("Pick up again your tiles:");
                objectOutputStream.flush();
                objectOutputStream.reset();
                objectOutputStream.writeInt(0);
                objectOutputStream.writeObject(new Message("server",nickname,PICKEDTILE,getColumn(), getTilesVector()));
                tilesVector.clear();
            }
            case WRONG_PLAYER,FAIL -> {
                System.out.println("Some big unexpected and impossible error occur.");
            }
            case COMMONOBJ -> {
                view.printCommonOBJ(message.getPayload());
            }
            case ENDGAME -> {
                System.out.println("The game has ended, you reached:");
                LinkedList<Player> vectorLinkedList = (LinkedList<Player>) message.getPayload();
                Player winner = (Player) message.getPayload2();
                for(Player player : vectorLinkedList){
                    view.printEndGamePoint(player);
                    view.printPersonalOBJ(player);
                }
                view.printWinner(winner);
            }

        }
    }
    private int validateInput(){
        int x=-1;
        boolean wentToCatch;
            do{
                try{
                    wentToCatch = false;
                    x = scanner.nextInt()-1;
            }catch (InputMismatchException e){
                    scanner.next();
                    wentToCatch = true;
                    e.printStackTrace();
            }}while(wentToCatch);
        return x;
    }
    private int getColumn(){
        System.out.println("Column of the library: ");
        return validateInput();
    }
    private Vector<Tiles> getTilesVector(){
        Vector<Tiles> tmp = new Vector<>();
        System.out.println("How many tiles do you want?");
        boolean wentToCatch;
        int len=0;
        do{
            try{
                wentToCatch = false;
                len = scanner.nextInt();
            }catch (InputMismatchException e){
                scanner.next();
                wentToCatch = true;
            }}while(wentToCatch);

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
