package it.polimi.ingsw.Client.GUI.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class GUI extends Application {
    private Stage stage;
    private Scene currentScene;
    private HashMap<String, Scene> nameToScene = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        setup();
        this.stage = stage;
        this.stage.setResizable(false);
            //richiamare startgame quando cliccato
            //richiamare FXML main controller
        showTheScene();

    }

    /**
     * Shows the first scene
     */
    public void showTheScene(){
        stage.setScene(currentScene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.setTitle("MyShelfie");
        stage.show();
    }

    /**
     * Shows the new scene
     * @param scene
     */
    public void changeTheScene( String scene){
        this.currentScene = nameToScene.get(scene);
        stage.setScene(currentScene);
        stage.show();
    }
    /**
     * Set up of the FXML file of the application.
     */
    public void setup(){
        //font da stampare
        try {
            for (Scenes scene : Scenes.values()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/it/polimi/MyShelfie/fxml/setup.fxml"));
                nameToScene.put(scene.getName(), new Scene(fxmlLoader.load()));
            }
            currentScene = nameToScene.get("SETUP");

        }catch(Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    /***
     * Main of the class
     * @param args Main args
     */
    public static void main(String[] args) {
        launch(args);

    }

}
