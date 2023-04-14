package it.polimi.ingsw.model.personalStrategy;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Player;

import java.util.Timer;
import java.util.Vector;

public interface PersonalObj {

    int check(Player player);
    Vector<Tiles> getPosition();
}
