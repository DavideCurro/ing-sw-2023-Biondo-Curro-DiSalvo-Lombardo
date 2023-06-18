package it.polimi.ingsw.Client.GUI.Controllers;

import it.polimi.ingsw.Client.ClientView;
import it.polimi.ingsw.Client.MessageDispatcher;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Playground;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import static javafx.application.Application.launch;

public class GUI extends ClientView {

    //private static HashMap<String, Scene> nameToScene = new HashMap<>();

   public static void changescene(){

        mainMenuController mainmenu = new mainMenuController();
        Platform.runLater(()-> SceneController.changerootPane(mainmenu, "/it/polimi/MyShelfie/fxml/mainMenu.fxml"));
    }

    @Override
    public void printPlayground(Playground ground) {
        mainMenuController mainmenu = (mainMenuController) SceneController.getActiveController();
        mainmenu.printplaygroundBoard(ground);

    }

    @Override
    public void printPersonalOBJ(Player player) {
        mainMenuController mainmenu = (mainMenuController) SceneController.getActiveController();
        mainmenu.printPersonalGoal(player);

    }

   /* @Override
    public void printCommonOBJ(int type) {
        mainMenuController mainmenu = (mainMenuController) SceneController.getActiveController();
        mainmenu.printCommonOBJ(type,is1);
    }*/

    //da finire
    @Override
    public void printPlayerLibrary(Player player) {
        mainMenuController mainmenu = (mainMenuController) SceneController.getActiveController();
        mainmenu.printLibrary(player);

    }

    @Override
    public void printPersonalPoint(Player player) {
        mainMenuController mainmenu = (mainMenuController) SceneController.getActiveController();
        mainmenu.setPrivatePoints(player);

    }

    /**
     * Picktile
     */
    //public void picktile



    /**
     * Shows the first scene
     */
   /* public void showTheScene(){
        stage.setScene(currentScene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.setTitle("MyShelfie");
        stage.show();
    }*/

    /**
     * Shows the new scene
     * @param scene
     */
   /* public static void changeTheScene(String scene){
        System.out.println(scene);
        currentScene = nameToScene.get(scene);
        stage.setScene(currentScene);
        System.out.println("questa è la scena " + currentScene.toString());
       // Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        //stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        //stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }*/



}
