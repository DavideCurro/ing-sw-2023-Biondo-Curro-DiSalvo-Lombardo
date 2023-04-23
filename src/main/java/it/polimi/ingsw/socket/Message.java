package it.polimi.ingsw.socket;

import java.io.Serializable;
public class Message implements Serializable {

    private final String receiverName; //so only the right client reads this message
    private String sender;
    private final Content messageType;
    private Object payload;

    public void setSender(String sender){
        this.sender = sender;
    }
    public Message(String receiverName, String sender, Content messageType, Object payload){
        this.receiverName = receiverName;
        this.sender = sender;
        this.messageType = messageType;
        this.payload = payload;
    }

    public Message(String receiverName, Content messageType){
        this.receiverName = receiverName;
        this.messageType = messageType;
    }
    public Message(String receiverName, Content messageType, Object payload){
        this.receiverName = receiverName;
        this.messageType = messageType;
        this.payload = payload;
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
    public Object getPayload(){return payload;}
    @Override
    public String toString(){
        String receivedMess;
        receivedMess = "Received this Message: \n" + "SENDER =" +sender +"\n" + "content" + messageType;
        return receivedMess;
    }
}
