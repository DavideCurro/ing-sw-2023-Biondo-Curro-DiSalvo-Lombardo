package it.polimi.ingsw.Client;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import static it.polimi.ingsw.Message.Content.NICKNAME;
import static it.polimi.ingsw.Message.Content.PICKEDTILE;

public class MessageDispatcher {
    private final Socket socket;
    private String nickname;
    private final ObjectOutputStream outputStream;
    public MessageDispatcher(Socket socket, ObjectOutputStream objectOutputStream){
        this.outputStream = objectOutputStream;
        this.socket = socket;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public boolean sendLoginInfo(int lobbyType){
        try {
            System.out.println("Sending info");
            outputStream.writeObject(new Message("Server",nickname, NICKNAME,lobbyType));
            System.out.println("Info sent");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return false;
    }
    public void reset(){
        try {
            outputStream.flush();
            outputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while connection");
            System.exit(2);
        }

    }
    public void sendPickUpData(Vector<Tiles> tiles, int column){
        try{
            outputStream.writeInt(1);
            outputStream.writeObject(new Message("server",nickname,PICKEDTILE,column, tiles));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error while sending information");
            System.exit(3);
        }
    }
    public void sendNickname(String nickname){
        setNickname(nickname);
        reset();
        try{
            outputStream.writeObject(new Message("Server",nickname, NICKNAME,nickname));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error while contacting server");
            System.exit(4);
        }
    }
}
