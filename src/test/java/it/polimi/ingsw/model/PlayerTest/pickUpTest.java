package it.polimi.ingsw.model.PlayerTest;

import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.exception.CoordinateStateException;
import it.polimi.ingsw.model.exception.PlaygroundException;
import it.polimi.ingsw.model.personalStrategy.GoalP1;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class pickUpTest {
    Player player;
    Playground playground;
    @Before
    public void setPlayground() throws PlaygroundException {
        playground = new Playground(2);
        player = new Player(new GoalP1(),"Claudio");
    }

    @Test
    public void validatePickUp() throws CoordinateStateException {
        Vector<Tiles> tmp = new Vector<>();
        tmp.add(new Tiles(-1,1,3));
        tmp.add(new Tiles(-1,1,4));
       assertTrue( player.pickUp(playground,1,tmp));
    }
    @AfterEach
    public void reset(){
        player = null;
        playground = null;
    }

}
