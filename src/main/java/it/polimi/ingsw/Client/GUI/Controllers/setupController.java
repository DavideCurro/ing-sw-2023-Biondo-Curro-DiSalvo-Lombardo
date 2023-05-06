package it.polimi.ingsw.Client.GUI.Controllers;

import it.polimi.ingsw.socket.server.GameHandler;
import it.polimi.ingsw.socket.server.StarterServer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Scanner;

public class setupController {
    @FXML
    Button startGame;
    @FXML
    Label selectNickname;
    @FXML
    TextField writeNickname;


    public void initialize() {
        startGame.setVisible(false);
        selectNickname.setVisible(true);
        writeNickname.setVisible(true);
    }

    public void startGame(MouseEvent mouseEvent) {
        startGame.setVisible(true);
    }

}
