package it.polimi.ingsw.Client.GUI.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {

    Button but1;
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("resources/it.polimi.MyShelfie/fxml/setup.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            primaryStage.setTitle("Title");
            but1 = new Button();
            but1.setText("click the button");
            but1.setOnAction(e -> System.out.println("cliccato"));
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch(Exception e) {
            System.out.println(e);
        }

    }


    public static void main(String[] args) {
        launch();

    }

}
