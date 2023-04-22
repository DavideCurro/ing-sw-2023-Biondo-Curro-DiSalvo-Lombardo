package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Playground.Tiles;

import java.util.Timer;
import java.util.Vector;

public class GameHandler {
    private Controller controller;

    public GameHandler(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void handleNewPlayer(String nickname){
        this.controller.newPlayer(nickname);
    }

    public void handleNewTurn(int column, Vector<Tiles> picked){
        this.controller.newTurn(column,picked);
    }

    //gli arrivano le coordinate di dove vuole mettere le tiles che ha preso

    public void handleStartGame(){
        this.controller.startGame();
    }
    public void setNumOfPlayers(int numOfPlayers){
        this.controller.getMatch().setupPlayground(numOfPlayers);
    }

}
