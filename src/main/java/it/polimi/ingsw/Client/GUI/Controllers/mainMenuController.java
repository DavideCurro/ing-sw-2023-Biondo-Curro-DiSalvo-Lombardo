package it.polimi.ingsw.Client.GUI.Controllers;

import it.polimi.ingsw.Client.MessageDispatcher;

import it.polimi.ingsw.Model.CommonStrategy.CommonObj;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import it.polimi.ingsw.Utility.Message.Message;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class mainMenuController extends Playground{
    @FXML
    Label points;
    @FXML
    TextField privatePoints;
    @FXML
    ImageView commonG2;
    @FXML
    GridPane playground;
    @FXML
    Button otherPlayers;
    @FXML
    Button button0 = new Button("1");
    @FXML
    Button button1 = new Button("2");
    @FXML
    Button button2 = new Button("3");
    @FXML
    Button button3 = new Button("4");
    @FXML
    Button button4 = new Button("5");
    @FXML
    ImageView personalCard;
    @FXML
    ImageView commonG1;
    @FXML
    GridPane library;
    private MessageDispatcher messageDispatcher;
    private Vector<Tiles> tmp;

    private int column = 0;

    private final int TILE_WIDTH = 52;
    private final int TILE_HEIGHT = 52;

    private ArrayList<Image> tilesImg = new ArrayList<Image>();
    private double mouseGridX;
    private double mouseGridY;
    private final Button button = new Button("Confirm");


    public mainMenuController() {
        tmp = new Vector<>();
        tilesImg = new ArrayList<>();
        this.mouseGridX = 0.0;
        this.mouseGridY = 0.0;
        playground = new GridPane();
        privatePoints = new TextField();
        points = new Label();
        commonG1 = new ImageView();
        commonG2 = new ImageView();
        library = new GridPane();
        otherPlayers = new Button();
    }

    public mainMenuController(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;

    }

    public void initialize() {
        otherPlayers.setVisible(true);
        personalCard.setVisible(true);
        commonG1.setVisible(true);
        commonG2.setVisible(true);
        playground.setVisible(true);
        library.setVisible(true);
        button.setVisible(false);
        points.setVisible(true);
        privatePoints.setVisible(true);

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

                if(index != -1) {
                    ImageView img = new ImageView(tilesImg.get(index));

                    img.setFitHeight((TILE_HEIGHT));
                    img.setFitWidth((TILE_WIDTH));
                            playground.setConstraints(img,j,i);
                            playground.getChildren().add(img);
                }
            }
        }
    }

    /**
     * Prints the personal goal of the player
     *
     //* @param player
     */
  /*  public void printPersonalGoal(Player player) {
       switch (player.getPersonalObj().getPersonalObj().getType()){
           case 1-> {
               System.out.println("ciao");
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals.png").toExternalForm());
           }
           case 2 ->{
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals2.png").toExternalForm());

           }
           case 3 ->{
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals3.png").toExternalForm());

           }
           case 4 ->{
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals4.png").toExternalForm());

           }
           case 5 ->{
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals5.png").toExternalForm());

           }
           case 6 ->{
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals6.png").toExternalForm());
           }
           case 7 ->{
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals7.png").toExternalForm());

           }
           case 8 ->{
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals8.png").toExternalForm());

           }
           case 9 ->{
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals9.png").toExternalForm());

           }
           case 10 ->{
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals10.png").toExternalForm());

           }
           case 11 ->{
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals11.png").toExternalForm());

           }
           case 12 ->{
               personalCard = new ImageView(this.getClass().getResource("Personal_Goals12.png").toExternalForm());

           }

       }

    }


    public void printCommonOBJ(int type, boolean is1) {
        switch (type){

            case 1-> {
                System.out.println("ciao");
                if(is1)
                    commonG1 = new ImageView(this.getClass().getResource("/common_goal_cards/1.jpg").toExternalForm());
                else
                    commonG2 = new ImageView(this.getClass().getResource("/common_goal_cards/1.jpg").toExternalForm());
            }
            case 2 ->{
                if(is1)
                    commonG1 = new ImageView(this.getClass().getResource("/common_goal_cards/2.jpg").toExternalForm());
                else
                    commonG2 = new ImageView(this.getClass().getResource("/common_goal_cards/2.jpg").toExternalForm());
            }
            case 3 ->{
                if(is1)
                    commonG1= new ImageView(this.getClass().getResource("/common_goal_cards/3.jpg").toExternalForm());
                else
                    commonG2= new ImageView(this.getClass().getResource("/common_goal_cards/3.jpg").toExternalForm());
            }
            case 4 ->{
                if(is1)
                    commonG1 = new ImageView(this.getClass().getResource("/common_goal_cards/4.jpg").toExternalForm());
                else
                    commonG2 = new ImageView(this.getClass().getResource("/common_goal_cards/4.jpg").toExternalForm());
            }
            case 5 ->{
                if(is1)
                    commonG1 = new ImageView(this.getClass().getResource("/common_goal_cards/5.png").toExternalForm());
                else
                    commonG2 = new ImageView(this.getClass().getResource("/common_goal_cards/5.png").toExternalForm());
            }
            case 6 ->{
                if(is1)
                    commonG1 = new ImageView(this.getClass().getResource("/common_goal_cards/6.png").toExternalForm());
                else
                    commonG2 = new ImageView(this.getClass().getResource("/common_goal_cards/6.png").toExternalForm());
            }
            case 7 ->{
                if(is1)
                    commonG1 = new ImageView(this.getClass().getResource("/common_goal_cards/7.png").toExternalForm());
                else
                    commonG2 = new ImageView(this.getClass().getResource("/common_goal_cards/7.png").toExternalForm());
            }
            case 8 ->{
                if(is1)
                    commonG1 = new ImageView(this.getClass().getResource("/common_goal_cards/8.png").toExternalForm());
                else
                    commonG2 = new ImageView(this.getClass().getResource("/common_goal_cards/8.png").toExternalForm());
            }
            case 9 ->{
                if(is1)
                    commonG1 = new ImageView(this.getClass().getResource("/common_goal_cards/9.png").toExternalForm());
                else
                    commonG2 = new ImageView(this.getClass().getResource("/common_goal_cards/9.png").toExternalForm());
            }
            case 10 ->{
                if(is1)
                    commonG1 = new ImageView(this.getClass().getResource("/common_goal_cards/10.png").toExternalForm());
                else
                    commonG2 = new ImageView(this.getClass().getResource("/common_goal_cards/10.png").toExternalForm());
            }
            case 11 ->{
                if(is1)
                    commonG1 = new ImageView(this.getClass().getResource("/common_goal_cards/11.png").toExternalForm());
                else
                    commonG2 = new ImageView(this.getClass().getResource("/common_goal_cards/11.png").toExternalForm());
            }
            case 12 ->{
                if(is1)
                    commonG1 = new ImageView(this.getClass().getResource("/common_goal_cards/12.png").toExternalForm());
                else
                    commonG2 = new ImageView(this.getClass().getResource("/common_goal_cards/12.png").toExternalForm());
            }

        }


    }

    /**
     * Method that associate the image of the tile to the tile itself
     */
    public void initTilesimage() {
        tilesImg.add(new Image(Objects.requireNonNull(this.getClass().getResource("/resouces/item_tiles/Cornici1.1.png")).toExternalForm()));
        tilesImg.add(new Image(Objects.requireNonNull(this.getClass().getResource("/resouces/item_tiles/Trofei1.1.png")).toExternalForm()));
        tilesImg.add(new Image(Objects.requireNonNull(this.getClass().getResource("/resouces/item_tiles/Giochi1.1.png")).toExternalForm()));
        tilesImg.add(new Image(Objects.requireNonNull(this.getClass().getResource("/resouces/item_tiles/Libri1.1.png")).toExternalForm()));
        tilesImg.add(new Image(Objects.requireNonNull(this.getClass().getResource("/resouces/item_tiles/Gatti1.1.png")).toExternalForm()));
        tilesImg.add(new Image(Objects.requireNonNull(this.getClass().getResource("/resouces/item_tiles/Piante1.1.png")).toExternalForm()));

       /* tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Cornici1.2.png").toExternalForm()));
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
        tilesImg.add(new Image(this.getClass().getResource("/resouces/item_tiles/Piante1.3.png").toExternalForm()));*/

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
            messageDispatcher.sendPickUpData(tmp, column);
        });
        button1.setOnAction(e -> {
            column = 1;
            messageDispatcher.sendPickUpData(tmp, column);
        });
        button2.setOnAction(e -> {
            column = 2;
            messageDispatcher.sendPickUpData(tmp, column);
        });
        button3.setOnAction(e -> {
            column = 3;
            messageDispatcher.sendPickUpData(tmp, column);
        });
        button4.setOnAction(e -> {
            column = 4;
            messageDispatcher.sendPickUpData(tmp, column);
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

    public void setPrivatePoints(Player player){
         int x = player.getPrivatePoints();
         privatePoints.setText(String.valueOf(x));
    }
    /**
     * Prints an error
     * @param message
     */
    public void showerror (String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    /**
     * Prints an information from the server to the client
     * @param message
     */
    public void showmessage (String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NEWS");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    /**
     *
     * Handle new message, that arrives from server
     *
     * @param message  the message.
     */
    public void handleNewMessage(Message message) {
        switch (message.getMessageType()) {
            case NEWGAME -> printplaygroundBoard((Playground) message.getPayload());

            case PLAYERDATA -> {
                Player tmp = (Player) message.getPayload();
                //printPersonalGoal(tmp);
            }

            case PICKTILE -> {

            }
            case PICKEDTILE -> {
                Playground playgroundmodel = (Playground) message.getPayload();
                Player playermodel = (Player) message.getPayload2();
                printplaygroundBoard(playgroundmodel);
                showmessage("It was the turn of " + playermodel.getNickname());
                printLibrary(playermodel);
                //print dei unti dei giocatori e del giocatore

            }/*
            case NICKNAME_DUPLICATE -> {
                showerror("This nickname was already taken. Choose another one: \n");
                chooseNickname.setVisible(true);
                nickname1 = nickname.getText();
                messageDispatcher.setNickname(nickname1);
            }*/

            case COMMONOBJDONE -> {
                showmessage("You completed the common goal!");
            }

            case PERSONALOBJDONE -> {
                showmessage("You completed your personal goal!");
            }
            case PICKUPFAIL -> {
                showerror("SOMETHING WENT WRONG WITH YOUR CHOICE" + "\n" + "Pick up again your tiles!");
                messageDispatcher.reset();
                //mainmenu.pickTiles(MouseEvent mouseEvent, Node node);
            }
            case WRONG_PLAYER, FAIL -> {
                showerror("Some big unexpected and impossible error occur.");
            }
            case COMMONOBJ -> {
                CommonObj tmp = (CommonObj) message.getPayload();
             //   printCommonOBJ(tmp.getType(),commonG1);
                tmp = (CommonObj) message.getPayload2();
              //  printCommonOBJ(tmp.getType(),commonG2);
            }
            case ENDGAME -> {
                //scene endgame da implementare
            }

        }


    }
}

