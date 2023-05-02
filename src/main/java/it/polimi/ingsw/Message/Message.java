package it.polimi.ingsw.Message;

import java.io.Serializable;
public class Message implements Serializable {

    private final String receiverName; //so only the right client reads this message
    private String sender;
    private final Content messageType;
    private Object payload;
    private Object payload2;

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
    public Message(String receiverName, String sender, Content messageType, Object payload, Object payload2){
        this.receiverName = receiverName;
        this.messageType = messageType;
        this.sender = sender;
        this.payload = payload;
        this.payload2 = payload2;
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
    public Object getPayload2(){return payload2;}
    @Override
    public String toString(){
        String receivedMess;
        receivedMess = "Received this Message: \n" + "SENDER =" +sender +"\n" + "content" + messageType;
        return receivedMess;
    }
}
