package it.polimi.ingsw.socket.client;

import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.socket.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import static it.polimi.ingsw.socket.Content.*;

public class ServerHandler {

    private Socket socket;
    private ClientView view;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ServerHandler(InetAddress host, int port, ClientView view) throws IOException {
        socket =  new Socket(host.getHostName(), 2000);
        this.view = view;
        objectOutputStream  = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream  = new ObjectInputStream(socket.getInputStream());
    }
    public void cli() throws IOException, ClassNotFoundException {
        view.welcome();
        //Input name()
        try {
            objectOutputStream.writeObject(new Message("Server", NICKNAME,"Claudio"));
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
            case PING -> objectOutputStream.writeObject(new Message("Server","Claudio",ACK,""));
            case NEWGAME -> {
                System.out.println((String) message.getPayload());
                objectOutputStream.writeObject(new Message("Server","Claudio",NEWGAME,2));
            }
            case GAMECREATED -> {
                view.printPlayground((Playground)message.getPayload());
            }
        }
    }
}
