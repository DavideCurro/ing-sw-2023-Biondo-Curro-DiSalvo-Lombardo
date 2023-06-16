package it.polimi.ingsw.Client;

import it.polimi.ingsw.Server.GameHandlerRMI;
import it.polimi.ingsw.Utility.Message.Message;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.module.FindException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Vector;

import static it.polimi.ingsw.Utility.Message.Content.NICKNAME;
import static it.polimi.ingsw.Utility.Message.Content.PICKEDTILE;


/**
 * The class Message dispatcher
 */
public class MessageDispatcher {
    private final Socket socket;
    private final Registry registry;
    private GameHandlerRMI stub;

    private String nickname;
    private final ObjectOutputStream outputStream;
    private final boolean isRMI;

    /**
     *
     * It is a constructor.
     *
     * @param socket  the socket.
     * @param objectOutputStream  the object output stream.
     */
    public MessageDispatcher(Socket socket, ObjectOutputStream objectOutputStream,boolean isRMI){

        this.outputStream = objectOutputStream;
        this.socket = socket;
        this.isRMI = isRMI;
        registry = null;
        stub = null;
    }
    public MessageDispatcher(Registry registry, GameHandlerRMI rmi, boolean isRMI){
        outputStream = null;
        socket = null;
        this.isRMI = isRMI;
        this.registry = registry;
        this.stub = rmi;
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
    public void setStub(GameHandlerRMI stub){
        this.stub =stub;
    }

    /**
     *
     * Send login info
     *
     * @param lobbyType  the lobby type.
     * @return boolean
     */
    public boolean sendLoginInfo(int lobbyType){ //SOLO SOCKET

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
        if(isRMI) return;

        try {
            outputStream.flush();
            outputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while connection");
            System.exit(2);
        }

    }
    public void sendPickUpData(Vector<Tiles>tiles , int column){
        if(isRMI){
            try {
                stub.handleTurn(column,tiles,nickname);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            sendPickUpDataSocket(tiles,column);
        }
    }

    /**
     *
     * Send pick up data
     *
     * @param tiles  the tiles.
     * @param column  the column.
     */
    public void sendPickUpDataSocket(Vector<Tiles> tiles, int column){

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
        if(isRMI){
            try {
                stub.handleNicknameFail(nickname);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        sendNicknameSocket(nickname);
    }

    /**
     *
     * Send nickname
     *
     * @param nickname  the nickname.
     */
    public void sendNicknameSocket(String nickname){

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
