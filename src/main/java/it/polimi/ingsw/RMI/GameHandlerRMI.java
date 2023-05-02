package it.polimi.ingsw.RMI;

import it.polimi.ingsw.model.Playground.Tiles;

import java.util.Vector;

public interface GameHandlerRMI{
        boolean handleLogin(String nickname);
        boolean handleTurn(int column, Vector<Tiles> tilesPicked);
}
