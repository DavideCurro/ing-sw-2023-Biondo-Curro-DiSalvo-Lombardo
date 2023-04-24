package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.socket.Content;
import it.polimi.ingsw.socket.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class MessageDispatcher {
    private ObjectInputStream inputStream;
    private ObjectOutputStream objectOutputStream;
    private Socket socket;
    private Message writeMessage;
    private Message readMessage;
    private static final Logger log = Logger.getLogger(Message.class.getName());

    public MessageDispatcher(Socket socket,ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream){
        this.socket = socket;
        this.objectOutputStream = objectOutputStream;
        this.inputStream = objectInputStream;
    }

    public void send(SenderCode senderCode, String reciver){
        switch (senderCode){
            case ERROR_CODE -> {
                writeMessage = new Message(reciver, Content.FAIL);
                try {
                    this.objectOutputStream.writeObject(writeMessage);
                }catch (IOException e){
                    log.severe("ERROR CLIENT NOT RESPONDING");
                }
            }
        }
    }
    public Message read(){
        try {
            readMessage = (Message) this.inputStream.readObject();
        }catch (IOException | ClassNotFoundException e){
            log.severe("ERROR CLIENT NOT RESPONDING");
        }
        return readMessage;
    }

}
