package it.polimi.ingsw.Client.GUI.Controllers;

import it.polimi.ingsw.Client.MessageDispatcher;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import javafx.fxml.FXML;
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

    private int columnp;
    private int rowsp;

    private final int TILE_WIDTH = 52;
    private final int TILE_HEIGHT = 52;

    private ArrayList<Image> tilesImg = new ArrayList<Image>();


    public mainMenuController(MessageDispatcher message) {
        this.message = message;

    }

    public void initialize(){
        otherPlayers.setVisible(true);
        personalCard.setVisible(true);
        commonG1.setVisible(true);
        commonG2.setVisible(true);
        playground.setVisible(true);
        library.setVisible(true);

        //buttons to choose the column where to enter the tiles in the library
        button0.setVisible(false);
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        button4.setVisible(false);

    }

    public void printplaygroundBoard(Playground playgroundmodel){

        // playground.setStyle("-fx-background-image : url ");

        initTilesimage();

        for(int i = 0; i < 9; i++){
            for(int j = 0; i < 9; j++){
               int index =  playgroundmodel.getGround()[i][j].getType();

               ImageView img = new ImageView(tilesImg.get(index));
               img.setFitHeight((TILE_HEIGHT));
               img.setFitWidth((TILE_WIDTH));
               playground.getChildren().add(img);
            }
        }
    }

    public void printPersonalGoal(Player playermodel){

    }

    public void initTilesimage(){
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Cornici1.1.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Trofei1.1.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Giochi1.1.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Libri1.1.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Gatti1.1.png").toExternalForm()));
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Piante1.1.png").toExternalForm()));


    }

    //METODO DA IMPLEMENTARE SHOW PLAYGROUND

    //pick tiles from playground
    public void pickTiles(MouseEvent mouseEvent) {
        tmp = new Vector<>();

        Button button = new Button("Confirm");
        button.setVisible(false);

        int x1 = (int) (playground.getLayoutX());
        int y1 = (int) (playground.getLayoutY());

        tmp.add(new Tiles(-1,x1,y1));
        button.setVisible(true);

        button.setOnAction(e ->{
            button0.setVisible(true);
            button1.setVisible(true);
            button2.setVisible(true);
            button3.setVisible(true);
            button4.setVisible(true);
        } );

        button0.setOnAction(e -> {
            column = 0;
            message.sendPickUpData(tmp,column);
        });
        button1.setOnAction(e ->{
            column = 1;
            message.sendPickUpData(tmp,column);
        });
        button2.setOnAction(e -> {
            column = 2;
            message.sendPickUpData(tmp,column);
        });
        button3.setOnAction(e -> {
            column = 3;
            message.sendPickUpData(tmp,column);
        });
        button4.setOnAction(e -> {
            column = 4;
            message.sendPickUpData(tmp,column);
        });

    }


}
