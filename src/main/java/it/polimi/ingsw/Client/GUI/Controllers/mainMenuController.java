package it.polimi.ingsw.Client.GUI.Controllers;

import it.polimi.ingsw.Client.MessageDispatcher;
import it.polimi.ingsw.Model.Playground.Tiles;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Vector;

public class mainMenuController {
    @FXML
    ImageView commonG2;
    @FXML
    GridPane playground;
    @FXML
    Button otherPlayers;
    @FXML
    ImageView personalCard;
    @FXML
    ImageView commonG1;
    @FXML
    GridPane library;
    private MessageDispatcher message;

    public mainMenuController(MessageDispatcher message) {
        this.message = message;
    }

    /**
     *
     * @param
     */

    //pick tiles from playground
    public void pickTiles(MouseEvent mouseEvent) {
        Vector<Tiles> tmp = new Vector<>();

        int x1 = (int) (playground.getLayoutX());
        int y1 = (int) (playground.getLayoutY());

        tmp.add(new Tiles(-1,x1,y1));

        Button button = new Button("Confirm");
        button.setOnAction(e -> insertTiles(tmp));

        //objectOutput.write(new Message("io","tu", Content.PICKEDTILE,column,tmp));

    }
    
    public void insertTiles( Vector<Tiles> t) {
        library.setOnMouseClicked(e-> {
            int column = (int) library.getLayoutY();
            message.sendPickUpData(t,column);

        });

    }

    public void insertTiles(MouseEvent mouseEvent) {
    }
}
