package it.polimi.ingsw.Client.GUI.Controllers;

import it.polimi.ingsw.Client.MessageDispatcher;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class mainMenuController {
    @FXML
    ImageView commonG2;
    @FXML
    GridPane playground;
    @FXML
    Button otherPlayers;
    @FXML
    Button button0, button1, button2, button3, button4;
    @FXML
    ImageView personalCard;
    @FXML
    ImageView commonG1;
    @FXML
    GridPane library;
    private MessageDispatcher message;
    private Vector<Tiles> tmp;

    private int column = 0;

    private final int TILE_WIDTH = 52;
    private final int TILE_HEIGHT = 52;

    private ArrayList<Image> tilesImg = new ArrayList<Image>();
    private double mouseGridX;
    private double mouseGridY;
    Button button = new Button("Confirm");


    public mainMenuController(MessageDispatcher message) {
        this.message = message;

    }

    public void initialize() {
        otherPlayers.setVisible(true);
        personalCard.setVisible(true);
        commonG1.setVisible(true);
        commonG2.setVisible(true);
        playground.setVisible(true);
        library.setVisible(true);
        button.setVisible(false);

        //buttons to choose the column where to enter the tiles in the library
        button0.setVisible(false);
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        button4.setVisible(false);

    }

    /**
     * Prints the playground everytime the server sends it to the client
     *
     * @param playgroundmodel
     */
    public void printplaygroundBoard(Playground playgroundmodel) {

        // playground.setStyle("-fx-background-image : url ");

        initTilesimage();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; i < 9; j++) {
                int index = playgroundmodel.getGround()[i][j].getType();

                ImageView img = new ImageView(tilesImg.get(index));
                img.setFitHeight((TILE_HEIGHT));
                img.setFitWidth((TILE_WIDTH));
                playground.getChildren().add(img);
            }
        }
    }

    /**
     * Prints the personal goal of the player
     *
     * @param playermodel
     */
    public void printPersonalGoal(Player playermodel) {
    }

    public void printCommonGoal(Player playermodel) {

    }

    /**
     * Method that associate the image of the tile to the tile itself
     */
    public void initTilesimage() {
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Cornici1.1.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Trofei1.1.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Giochi1.1.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Libri1.1.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Gatti1.1.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Piante1.1.png").toExternalForm()));

        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Cornici1.2.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Trofei1.2.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Giochi1.2.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Libri1.2.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Gatti1.2.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Piante1.2png").toExternalForm()));

        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Cornici1.3.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Trofei1.3.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Giochi1.3.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Libri1.3.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Gatti1.3.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Piante1.3.png").toExternalForm()));

    }

    /**
     * pick tiles from playground
     */
    public void pickTiles(MouseEvent mouseEvent) {
        tmp = new Vector<>();

        button.setVisible(true);

        playground.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            mouseGridX = e.getX();
            mouseGridY = e.getY();

        });


        tmp.add(new Tiles(-1, (int) mouseGridX, (int) mouseGridY));
    }

    /**
     * Inserts the picked tiles from the playground, in the library of the player, in the column chosen by a button
     *
     * @param mouseEvent
     */
    public void insertTiles(MouseEvent mouseEvent) {


        button.setOnAction(e -> {
            button0.setVisible(true);
            button1.setVisible(true);
            button2.setVisible(true);
            button3.setVisible(true);
            button4.setVisible(true);
        });

        button0.setOnAction(e -> {
            column = 0;
            message.sendPickUpData(tmp, column);
        });
        button1.setOnAction(e -> {
            column = 1;
            message.sendPickUpData(tmp, column);
        });
        button2.setOnAction(e -> {
            column = 2;
            message.sendPickUpData(tmp, column);
        });
        button3.setOnAction(e -> {
            column = 3;
            message.sendPickUpData(tmp, column);
        });
        button4.setOnAction(e -> {
            column = 4;
            message.sendPickUpData(tmp, column);
        });

    }

    /**
     * Prints the library of a player
     *
     * @param playermodel
     */
    public void printLibrary(Player playermodel) {
        otherPlayers.setOnAction(e -> {

            //prints the library of the players
        });

    }
}


