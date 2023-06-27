package it.polimi.ingsw.NotWorking.GUI.Controllers;



import it.polimi.ingsw.Client.MessageDispatcher;
import it.polimi.ingsw.Model.Playground.Tiles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class setupController implements GenericSceneController, Initializable {
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

    private boolean buttonpressed;
    private Socket socket;
    private GUI gui;
    private String currentScene;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Vector<Tiles> tilesVector;
    private MessageDispatcher messageDispatcher;
    private mainMenuController mainmenu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StartGame.setVisible(true);
        chooseNickname.setVisible(true);
        nickname.setVisible(true);

    }
    public setupController(){
        this.socket = null;
        this.objectOutputStream = null;
        this.objectInputStream = null;
        this.tilesVector = new Vector<>();
       //mainmenu = new mainMenuController();
        //        StartGame = new Button("StartGame");
        //chooseNickname = new Label("Choose Nickname");
        //nickname = new TextField();
        //lobby2 = new RadioButton();
        //lobby3 = new RadioButton();
        //lobby4 = new RadioButton();

        //this.gui = gui;
        //initialize();

       /* try {
            setupSocket(InetAddress.getLocalHost(),2000);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /*public void setupSocket(InetAddress host, int port) throws IOException {
        socket = new Socket(host.getHostName(), port);
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
    }*/


    public String getNickname(){
        return nickname.getText();
    }

    public int getLobby(){
        int lobbyType = 0;
        if (lobby2.isSelected()){
            lobbyType = 2;
        }
        else if (lobby3.isSelected()){
            lobbyType = 3;
        }
        else if (lobby4.isSelected()){
            lobbyType = 4;
        }
        return lobbyType;

    }


    /**
     * Method that starts the game once the nickname and the lobby have been chosen
     * @param
     */
    public void startgame(ActionEvent actionEvent) {
        StartGame.setOnAction(e-> {
            System.out.println("Funziona");

            buttonpressed = true;

        });

    }

    public boolean isButtonpressed() {
        return buttonpressed;
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
    public void showmessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NEWS");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

}

