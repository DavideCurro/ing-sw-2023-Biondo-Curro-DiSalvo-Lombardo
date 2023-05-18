package it.polimi.ingsw.Client;

import it.polimi.ingsw.Utility.Message.Message;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import static it.polimi.ingsw.Utility.Message.Content.NICKNAME;
import static it.polimi.ingsw.Utility.Message.Content.PICKEDTILE;


/**
 * The class Message dispatcher
 */
public class MessageDispatcher {
    private final Socket socket;
    private String nickname;
    private final ObjectOutputStream outputStream;

    /**
     *
     * It is a constructor.
     *
     * @param socket  the socket.
     * @param objectOutputStream  the object output stream.
     */
    public MessageDispatcher(Socket socket, ObjectOutputStream objectOutputStream){

        this.outputStream = objectOutputStream;
        this.socket = socket;
    }

    /**
     *
     * Sets the nickname
     *
     * @param nickname  the nickname.
     */
    public void setNickname(String nickname){

        this.nickname = nickname;
    }

    /**
     *
     * Send login info
     *
     * @param lobbyType  the lobby type.
     * @return boolean
     */
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

    /**
     *
     * Reset the output stream
     *
     */
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

    /**
     *
     * Send pick up data
     *
     * @param tiles  the tiles.
     * @param column  the column.
     */
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

    /**
     *
     * Send nickname
     *
     * @param nickname  the nickname.
     */
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
