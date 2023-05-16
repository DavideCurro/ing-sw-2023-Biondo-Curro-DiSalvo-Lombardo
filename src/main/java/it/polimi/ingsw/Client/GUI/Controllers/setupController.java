package it.polimi.ingsw.Client.GUI.Controllers;

//import it.polimi.ingsw.socket.server.GameHandler;
//import it.polimi.ingsw.socket.server.StarterServer;
import it.polimi.ingsw.Client.MessageDispatcher;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;



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
    private final mainMenuController mainmenu;


    public setupController(InetAddress host, int port, mainMenuController mainmenu) throws IOException {
        this.socket = new Socket(host.getHostName(), port);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        tilesVector = new Vector<>();
        messageDispatcher = new MessageDispatcher(socket, objectOutputStream);
        this.mainmenu = mainmenu;
    }


    public void initialize() {
        StartGame.setVisible(true);
        chooseNickname.setVisible(true);
        nickname.setVisible(true);
    }

    public void gui() throws InterruptedException {

        int gamestart = 0;

        nickname1 = nickname.getText();
        messageDispatcher.setNickname(nickname1);

        if (lobby2.isSelected()) lobbyType = 2;
        else if (lobby3.isSelected()) lobbyType = 3;
        else if (lobby4.isSelected()) lobbyType = 4;

                    //setPlayerNum(lobbyType)

        if (!messageDispatcher.sendLoginInfo(lobbyType))
            showerror("ERROR!");

            while (socket.isConnected()) {
                Message message;
                try {
                    message = (Message) objectInputStream.readObject();
                    if (gamestart == 0)
                        gamestart++;
                    handleNewMessage(message);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        sleep(5000);
    }



    public void StartGame(MouseEvent mouseEvent) {

        StartGame = new Button();
        StartGame.setOnAction(e-> {
            try {
                gui();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });


    }

    public void showerror(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public void handleNewMessage(Message message) {
        switch (message.getMessageType()) {
            case NEWGAME ->
                    mainmenu.printplaygroundBoard((Playground)message.getPayload());
            case PLAYERDATA -> {
                    Player tmp = (Player) message.getPayload();
                mainmenu.printPersonalGoal(tmp);
            }

            /**
             * messaggi che arrivano dal server.
             * vari casi di gioco
             *
             * PICK TILE
             *
             * SHOW LIBRARY
             *
             * COMMON GOAL
             *
             * PERSONAL GOAL
             */

        }

    }

}