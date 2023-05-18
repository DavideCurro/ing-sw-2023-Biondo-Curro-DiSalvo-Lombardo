package it.polimi.ingsw.Model.PersonalStrategy;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.Player.Player;

import java.util.Vector;

public interface PersonalObj {

    int check(Player player);
    Vector<Tiles> getPosition();

    int getType();
}
