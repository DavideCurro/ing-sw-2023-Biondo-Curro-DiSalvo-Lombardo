package it.polimi.ingsw.socket.client;

import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.socket.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import static it.polimi.ingsw.socket.Content.*;

public class ServerHandler {

    private Socket socket;
    private ClientView view;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Vector<Tiles> tilesVector;
    private Scanner scanner;
    private String nickname;

    public ServerHandler(InetAddress host, int port, ClientView view) throws IOException {
        socket =  new Socket(host.getHostName(), 2000);
        socket.setSoTimeout(0);
        this.view = view;
        objectOutputStream  = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream  = new ObjectInputStream(socket.getInputStream());
        tilesVector = new Vector<>();
        scanner = new Scanner(System.in);
        nickname = scanner.nextLine();
    }
    public void cli() throws IOException, ClassNotFoundException {
        view.welcome();
        //Input name()
        try {
            objectOutputStream.writeObject(new Message("Server", NICKNAME,nickname));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (socket.isConnected()){
            Message message = null;
            try {
                message = (Message) objectInputStream.readObject();
                handleNewMessage(message);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void handleNewMessage(Message message) throws IOException {
        switch (message.getMessageType()){
            case GAMEJOIN ->{
                view.printPersonalOBJ((Player) message.getPayload());
            }
            case PING ->{
                System.out.println("Ping received from Server");
                objectOutputStream.writeObject(new Message("Server",nickname,ACK,""));
            }
            case NEWGAME -> {
                System.out.println((String) message.getPayload());
                objectOutputStream.writeObject(new Message("Server",nickname,NEWGAME,2));
            }
            case GAMECREATED, PLAYGROUND -> {
                view.printPlayground((Playground)message.getPayload());
            }
            case PICKTILES -> {
                objectOutputStream.writeObject((new Message("Server",nickname,COORDINATE,1)));
                tilesVector.add(new Tiles(-1,1,3));
                objectOutputStream.writeObject((new Message("Server",nickname,TILESPICKED,tilesVector)));
            }
            case SUCCESS -> {
                Player tmp = (Player) message.getPayload();
                view.printPlayerLibrary(tmp);
            }

        }
    }
}
