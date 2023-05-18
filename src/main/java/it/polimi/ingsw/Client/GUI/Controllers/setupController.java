package it.polimi.ingsw.Client.GUI.Controllers;

//import it.polimi.ingsw.socket.server.GameHandler;
//import it.polimi.ingsw.socket.server.StarterServerSocket;
import it.polimi.ingsw.Client.MessageDispatcher;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import javafx.fxml.FXML;
import javafx.scene.Node;
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


    /**
     * Method that starts the game once the nickname and the lobby have been chosen
     * @param mouseEvent
     */
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

    /**
     *
     * Handle new message, that arrives from server
     *
     * @param message  the message.
     */
    public void handleNewMessage(Message message) {
        switch (message.getMessageType()) {
            case NEWGAME ->
                mainmenu.printplaygroundBoard((Playground)message.getPayload());

            case PLAYERDATA -> {
                Player tmp = (Player) message.getPayload();
                mainmenu.printPersonalGoal(tmp);
            }

            case PICKTILE ->{

            }
            case PICKEDTILE -> {
                Playground playgroundmodel = (Playground) message.getPayload();
                Player playermodel =(Player)message.getPayload2();
                mainmenu.printplaygroundBoard(playgroundmodel);
                showmessage("It was the turn of "+ playermodel.getNickname());
                mainmenu.printLibrary(playermodel);
                //print dei unti dei giocatori e del giocatore

            }
            case NICKNAME_DUPLICATE -> {
                showerror("This nickname was already taken. Choose another one: \n");
                chooseNickname.setVisible(true);
                nickname1 = nickname.getText();
                messageDispatcher.setNickname(nickname1);
            }

            case COMMONOBJDONE ->{
                showmessage("You completed the common goal!");
            }

            case PERSONALOBJDONE -> {
                showmessage("You completed your personal goal!");
            }
            case PICKUPFAIL -> {
                showerror("SOMETHING WENT WRONG WITH YOUR CHOICE"+ "\n" +"Pick up again your tiles!");
                messageDispatcher.reset();
                //mainmenu.pickTiles(MouseEvent mouseEvent, Node node);
            }
            case WRONG_PLAYER,FAIL -> {
                showerror("Some big unexpected and impossible error occur.");
            }
            case COMMONOBJ -> {

            }
            case ENDGAME -> {
                //scene endgame da implementare
            }

        }

    }

}