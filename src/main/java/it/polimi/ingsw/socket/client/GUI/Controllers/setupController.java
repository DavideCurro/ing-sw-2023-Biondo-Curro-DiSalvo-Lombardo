package it.polimi.ingsw.socket.client.GUI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class setupController {
    @FXML
    Button startGame;
    public void startGame(MouseEvent mouseEvent) {
        startGame.setText("Benvenuto");
    }
}
