package it.polimi.ingsw.Client.GUI.Controllers;



import it.polimi.ingsw.Client.MessageDispatcher;
import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Utility.Message.Message;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Vector<Tiles> tilesVector;
    private MessageDispatcher messageDispatcher;
    private mainMenuController mainmenu;


    public setupController(){
        this.socket = null;
        this.objectOutputStream = null;
        this.objectInputStream = null;
        this.tilesVector = new Vector<>();
        this.mainmenu = null;
    }

    public setupController(InetAddress host, int port) throws IOException {
        this.socket = new Socket(host.getHostName(), port);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        tilesVector = new Vector<>();
        messageDispatcher = new MessageDispatcher(socket, objectOutputStream,false);
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream){
        this.objectOutputStream = objectOutputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream){
        this.objectInputStream = objectInputStream;
    }

    public void setMainmenu(mainMenuController mainmenu){
        this.mainmenu = mainmenu;
    }


    public void initialize() {
        StartGame.setVisible(true);
        chooseNickname.setVisible(true);
        nickname.setVisible(true);
    }

    public void gui() throws InterruptedException {

        int gamestart = 0;
System.out.println("bonasira");
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
                    mainmenu.handleNewMessage(message);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        sleep(5000);
    }


    /**
     * Method that starts the game once the nickname and the lobby have been chosen
     * @param
     */
    public void StartGame() {

        StartGame = new Button();
        StartGame.setOnAction(e-> {
            try {
                gui();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });


    }

    /**
     * Prints an error
     * @param message
     */
    public void showerror(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    /**
     * Prints an information from the server to the client
     * @param message
     */
    public void showmessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NEWS");
        alert.setHeaderText(message);
        alert.showAndWait();
    }



}

