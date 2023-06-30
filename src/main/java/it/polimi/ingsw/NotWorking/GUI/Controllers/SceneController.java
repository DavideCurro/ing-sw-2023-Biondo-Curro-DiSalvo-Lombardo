/*package it.polimi.ingsw.NotWorking.GUI.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

    private static Scene onGoingScene;
    private static GenericSceneController activeController;
    private static Scene activeScene;


    public static Scene getOnGoingScene() {
        return onGoingScene;
    }

    public static GenericSceneController getActiveController() {
        return activeController;
    }



    public static void changerootPane(GenericSceneController controller, Scene scene, String fxml){
        try{
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/it/polimi/MyShelfie/fxml/" + fxml));

            loader.setController(controller);
            activeController = controller;
            Parent root = loader.load();

            onGoingScene = scene;
            onGoingScene.setRoot(root);

        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public static void changerootPane(GenericSceneController controller, String fxml) {
        changerootPane(controller, onGoingScene, fxml);
    }

    public static void setactivescene(Scene scene) {

    }

    public static void setStage(Stage stage) {
    }

    public static void setactivecontroller(GenericSceneController controller) {
        activeController = controller;
    }
}
*/