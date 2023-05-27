package it.polimi.ingsw.Client.GUI.Controllers;

import it.polimi.ingsw.Client.MessageDispatcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class GUI extends Application implements Runnable {
    private static Stage stage;
    private Socket socket;
    private static Scene currentScene;
    private static HashMap<String, Scene> nameToScene = new HashMap<>();

    private setupController setupcont;
    private MessageDispatcher messageDispatcher;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public GUI() throws IOException {
        stage = new Stage();
        setupcont = new setupController();
        setupcont.setGui(this);

    }

    /***
     * Main of the class
     * @param args Main args
     */
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws IOException {
        setup();
        System.out.println("no sacciu");
        GUI.stage = stage;
        GUI.stage.setResizable(false);
        showTheScene();
    }

    /**
     * Set up of the FXML file of the application.
     */
    public void setup() {
        //font da stampare
        try {
            for (Scenes scene : Scenes.values()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scene.getFile()));
                nameToScene.put(scene.getName(), new Scene(fxmlLoader.load()));
                System.out.println("non funzionaaaaaa");

            }
            currentScene = nameToScene.get("SETUP");


        } catch (Exception e) {
            e.printStackTrace();

        }
    }



    /**
     * Main method of the Thread Client GUI
     */
    @Override
    public void run(){

//needs to put stuff in
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
    public static void changeTheScene(String scene){
        System.out.println(scene);
        currentScene = nameToScene.get(scene);
        stage.setScene(currentScene);
        System.out.println("questa è la scena " + currentScene.toString());
       // Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        //stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        //stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }



}
