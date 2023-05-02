package it.polimi.ingsw.socket.client.GUI.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("setup.fxml"));
        stage.setTitle("Prova Start Game");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
