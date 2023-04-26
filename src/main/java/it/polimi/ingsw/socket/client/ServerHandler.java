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
        nickname = scanner.nextLine();
    }
    public void cli() throws InterruptedException {
        view.welcome();
        //Input name()
        try {
            objectOutputStream.writeObject(new Message("Server",nickname, NICKNAME,2));

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
                System.out.println("Il tuo obiettivo personale è");
                view.printPersonalOBJ(tmp);
            }
            case PICKTILE ->{
                tilesVector.add(new Tiles(-1,1,4));
                objectOutputStream.flush();
                objectOutputStream.reset();
                objectOutputStream.writeObject(new Message("server",nickname,PICKEDTILE,1, tilesVector));
                tilesVector.clear();
            }
            case PICKEDTILE -> {
                Playground playground = (Playground) message.getPayload();
                LinkedList<Player> vectorLinkedList = (LinkedList<Player>) message.getPayload2();
               // view.printPlayerLibrary(match.getLastPlayer());
                view.printPlayground(playground);
                for(Player player : vectorLinkedList){
                    view.printPlayerLibrary(player);
                    view.printOutPointsPerPlayer(player);
                }
            }

        }
    }
}
