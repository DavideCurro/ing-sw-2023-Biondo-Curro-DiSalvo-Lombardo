package it.polimi.ingsw.Client.GUI.Controllers;

//import it.polimi.ingsw.socket.server.GameHandler;
//import it.polimi.ingsw.socket.server.StarterServer;
import it.polimi.ingsw.Client.MessageDispatcher;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Playground.Tiles;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class setupController {
    @FXML
    Label chooselobby;
    @FXML
    TextField nickname;
    @FXML
    Label chooseNickname;
    @FXML
    Button StartGame;
    @FXML
    RadioButton lobby2;
    @FXML
    RadioButton lobby3;
    @FXML
    RadioButton lobby4;
    String nickname1;
    private int lobbyType = 0;

    private final Socket socket;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;
    private final Vector<Tiles> tilesVector;
    private MessageDispatcher messageDispatcher;


    public setupController(InetAddress host, int port) throws IOException {
        this.socket = new Socket(host.getHostName(), port);
        objectOutputStream  = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream  = new ObjectInputStream(socket.getInputStream());
        tilesVector = new Vector<>();
        messageDispatcher = new MessageDispatcher(socket,objectOutputStream);
    }


    public void initialize () {
            StartGame.setVisible(false);
            chooseNickname.setVisible(true);
            nickname.setVisible(true);
        }

        public void gui () throws InterruptedException {

            int gamestart = 0;

            nickname1 = nickname.getText();
            messageDispatcher.setNickname(nickname1);

            if(lobby2.isSelected()) lobbyType=2;
                else if (lobby3.isSelected()) lobbyType = 3;
                    else if (lobby4.isSelected()) lobbyType = 4;

                    //setPlayerNum(lobbyType)

            //fixare con finestra modale
            if(!messageDispatcher.sendLoginInfo(lobbyType)) System.out.println("ERROR!");

            while (socket.isConnected()) {
                Message message;
                try {
                    message = (Message) objectInputStream.readObject();
                    if(gamestart == 0)
                        gamestart++;
                    handleNewMessage(message);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            sleep(5000);
        }

    private void handleNewMessage(Message message) {
        switch (message.getMessageType()) {

            /**
             * messaggi che arrivano dal server.
             * vari casi di gioco
             */

        }

    }

    public void StartGame (MouseEvent mouseEvent){
        StartGame.setVisible(true);

        }

}
