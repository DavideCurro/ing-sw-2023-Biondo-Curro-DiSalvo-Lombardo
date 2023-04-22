package it.polimi.ingsw.socket;

import java.io.Serializable;
public class Message implements Serializable {

    private final String receiverName; //so only the right client reads this message
    private String sender;
    private final Content messageType;

    public void setSender(String sender){
        this.sender = sender;
    }
    public Message(String receiverName, String sender, Content messageType){
        this.receiverName = receiverName;
        this.sender = sender;
        this.messageType = messageType;
    }

    public Message(String receiverName, Content messageType){
        this.receiverName = receiverName;
        this.messageType = messageType;
    }

    public String getReceiverName(){
        return receiverName;
    }

    public String getSender(){
        return sender;
    }

    public Content getMessageType(){
        return messageType;
    }

    @Override
    public String toString(){
        String receivedMess;
        receivedMess = "Received this Message: \n" + "SENDER =" +sender +"\n" + "content" + messageType;
        return receivedMess;
    }
}
