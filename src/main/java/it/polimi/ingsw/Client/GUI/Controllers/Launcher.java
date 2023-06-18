package it.polimi.ingsw.Client.GUI.Controllers;

import it.polimi.ingsw.Client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    //inserisco prima scena(setup) in start
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/polimi/MyShelfie/fxml/setup.fxml"));

        Parent root = null;
        root = fxmlLoader.load();

        Scene scene = new Scene(root);
        SceneController.setactivescene(scene);
        stage.setScene((scene));

        //setupController controller = fxmlLoader.getController();

        SceneController.setStage(stage);
        //SceneController.setactivecontroller(controller);


        stage.show();



       /* Client client = new Client();
        String[] args = {"GUI"};
        client.setArgs(args);
        Thread thread = new Thread((Runnable) client);
        thread.start();*/


      /*  setup();
        System.out.println("no sacciu");
        GUI.stage = stage;
        GUI.stage.setResizable(false);
        showTheScene();*/
    }


}
