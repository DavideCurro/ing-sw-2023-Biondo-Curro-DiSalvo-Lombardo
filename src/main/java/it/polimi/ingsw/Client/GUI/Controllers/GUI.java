package it.polimi.ingsw.Client.GUI.Controllers;

import it.polimi.ingsw.Client.MessageDispatcher;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class GUI extends Application implements Runnable {
    private Stage stage;
    private Socket socket;
    private Scene currentScene;
    private HashMap<String, Scene> nameToScene = new HashMap<>();
    private setupController setupcont;
    private MessageDispatcher messageDispatcher;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public GUI() throws IOException {

        setupcont = new setupController();
    }

    @Override
    public void start(Stage stage) throws IOException {
        setup();
        this.stage = stage;
        this.stage.setResizable(false);
        showTheScene();
        //setupcont.setGui();//setted lobbyType e nickname
        //setupcont.startgame(new ActionEvent());
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
    public void changeTheScene(String scene){
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/setup.fxml"));
                nameToScene.put(scene.getName(), new Scene(fxmlLoader.load()));
            }
            currentScene = nameToScene.get("SETUP");

        }catch(Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }


    /**
     * Main method of the Thread Client GUI
     */
    @Override
    public void run(){

//needs to put stuff in
    }


    /***
     * Main of the class
     * @param args Main args
     */
    public static void main(String[] args) {
        launch(args);

    }

}
