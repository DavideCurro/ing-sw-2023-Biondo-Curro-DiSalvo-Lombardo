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
    private Vector<Tiles> tmp;

    private int column = 0;
    public mainMenuController(MessageDispatcher message) {
        otherPlayers.setVisible(true);
        personalCard.setVisible(true);
        commonG1.setVisible(true);
        commonG2.setVisible(true);
        playground.setVisible(true);
        library.setVisible(true);
        this.message = message;
    }

    /**
     *
     * @param
     */

    //pick tiles from playground
    public void pickTiles(MouseEvent mouseEvent) {
        tmp = new Vector<>();

        Button button = new Button("Confirm");
        button.setVisible(false);

        int x1 = (int) (playground.getLayoutX());
        int y1 = (int) (playground.getLayoutY());

        tmp.add(new Tiles(-1,x1,y1));
        button.setVisible(true);

        //buttons to choose the column where to enter the tiles in the library
        Button col0 = new Button("1");
        Button col1 = new Button("2");
        Button col2 = new Button("3");
        Button col3 = new Button("4");
        Button col4 = new Button("5");
        col0.setVisible(false);
        col1.setVisible(false);
        col2.setVisible(false);
        col3.setVisible(false);
        col4.setVisible(false);

        button.setOnAction(e ->{
            col0.setVisible(true);
            col1.setVisible(true);
            col2.setVisible(true);
            col3.setVisible(true);
            col4.setVisible(true);
        } );

        col0.setOnAction(e -> {
            column = 0;
            message.sendPickUpData(tmp,column);
        });
        col1.setOnAction(e ->{
            column = 1;
            message.sendPickUpData(tmp,column);
        });
        col2.setOnAction(e -> {
            column = 2;
            message.sendPickUpData(tmp,column);
        });
        col3.setOnAction(e -> {
            column = 3;
            message.sendPickUpData(tmp,column);
        });
        col4.setOnAction(e -> {
            column = 4;
            message.sendPickUpData(tmp,column);
        });

    }
}
