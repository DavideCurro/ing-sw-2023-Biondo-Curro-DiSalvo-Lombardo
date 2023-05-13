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

import static java.lang.Thread.sleep;


/**
 * The class cliHandler
 */
public class cliHandler {

    private final Socket socket;
    private final ClientView view;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;
    private final Vector<Tiles> tilesVector;
    private Scanner scanner;
    private String nickname;
    private MessageDispatcher messageDispatcher;


    /**
     *
     * It is a constructor.
     *
     * @param host  the host.
     * @param port  the port.
     * @param view  the view.
     * @throws   IOException, for the creation of socket and object-stream
     */
    public cliHandler(InetAddress host, int port, ClientView view) throws IOException {

        socket =  new Socket(host.getHostName(), port);
        socket.setSoTimeout(0);
        this.view = view;
        objectOutputStream  = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream  = new ObjectInputStream(socket.getInputStream());
        tilesVector = new Vector<>();
        scanner = new Scanner(System.in);
        nickname = "";
        messageDispatcher = new MessageDispatcher(socket,objectOutputStream);
    }

    /**
     *
     * Validate lobby type
     *
     * @return int
     */
    private int validateLobbyType(){


        int lobbyType = -1;
        boolean wentToCatch;
        do {
            view.printChooseLobby(nickname);
            do{
                try{
                    wentToCatch = false;
                    lobbyType = scanner.nextInt();
                } catch (InputMismatchException e){
                    scanner.next();
                    wentToCatch = true;
                    System.out.println("Re-enter the value!");
                    view.printChooseLobby(nickname);
                }
            }while(wentToCatch);
        }while (lobbyType <2 || lobbyType >4);
        return lobbyType;
    }


    /**
     *
     * Cli, this method handle every aspect from start-up of cli
     *
     * @throws   InterruptedException, this is needed for the sleep of the client
     */
    public void cli() throws InterruptedException {

        int gamestart = 0;

        view.welcome();

        System.out.print("Choose your nickname: ");
        nickname = scanner.nextLine();
        messageDispatcher.setNickname(nickname);

        int lobbyType = validateLobbyType();
        view.setPlayerNum(lobbyType);

        System.out.println("You're joining the lobby...");
        if(!messageDispatcher.sendLoginInfo(lobbyType)) System.out.println("ERROR!");

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


    /**
     *
     * Handle new message, that arrives from server
     *
     * @param message  the message.
     */
    private void handleNewMessage(Message message){

        switch (message.getMessageType()){
            case NEWGAME ->
                    view.printPlayground((Playground)message.getPayload());

            case PLAYERDATA -> {
                Player tmp = (Player) message.getPayload();
                System.out.println("Your personal goal is: \n");
                view.printPersonalOBJ(tmp);
            }
            case PICKTILE ->{
                messageDispatcher.reset();
                messageDispatcher.sendPickUpData(getTilesVector(),getColumn());
                tilesVector.clear();
            }
            case PICKEDTILE -> {
                Playground playground = (Playground) message.getPayload();
                Player player =(Player)message.getPayload2();
                view.printPlayground(playground);
                System.out.println("It was the turn of "+ player.getNickname() + " here it's his library");
                view.printPlayerLibrary(player);
                if(player.getNickname().equals(nickname)){
                    player.setPoints(0);
                    view.printPersonalPoint(player);
                }else {
                    view.printOutPointsPerPlayer(player);
                }
            }
            case NICKNAME_DUPLICATE -> {
                System.out.println("This nickname was already taken. Choose another one: \n");
                scanner = new Scanner(System.in);
                nickname = scanner.nextLine();
                messageDispatcher.sendNickname(nickname);
            }
            case COMMONOBJDONE ->{
                System.out.println("You completed the common goal!");
                Player player = (Player) message.getPayload();
                int[] countable = (int[]) message.getPayload2();
                switch (countable[0]){
                    case 1-> System.out.println("You made the first commonOBJ");
                    case 2-> System.out.println("You made the second commonOBJ");
                    case 3-> System.out.println("You made both commonOBJ!");
                }
                view.printOutPointsPerPlayer(player);
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
                messageDispatcher.reset();
                messageDispatcher.sendPickUpData(getTilesVector(),getColumn());
                tilesVector.clear();
            }
            case WRONG_PLAYER,FAIL -> {
                System.out.println("Some big unexpected and impossible error occur.");
            }
            case COMMONOBJ -> {
                view.printCommonOBJ(message.getPayload());
                view.printCommonOBJ(message.getPayload2());
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

    /**
     *
     * Validate input
     *
     * @return int
     */
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

    /**
     *
     * Gets the column
     *
     * @return the column
     */
    private int getColumn(){

        System.out.println("Column of the library: ");
        return validateInput();
    }

    /**
     *
     * Gets the tiles vector, by asking player how many tiles he wants
     *
     * @return the tiles vector
     */
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
