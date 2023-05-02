package it.polimi.ingsw.model.PlayerTest;

import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.exception.CoordinateStateException;
import it.polimi.ingsw.model.exception.PlaygroundException;
import it.polimi.ingsw.model.personalStrategy.GoalP1;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.Vector;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class pickUpInvalidTest {
    Player player;
    Playground playground;
    @Before
    public void setPlayground() throws PlaygroundException {
        playground = new Playground(4);
        player = new Player(new GoalP1(),"Claudio");
    }

    @Test
    public void alreadyPickedTest() throws CoordinateStateException {
        Vector<Tiles> tmp = new Vector<>();
        tmp.add(new Tiles(-1,0,0));

        assertFalse( player.pickUp(playground,1,tmp));
    }
    @Test
    public void notAdjacentTest() throws CoordinateStateException {
        boolean thrown = false;
            Vector<Tiles> tmp = new Vector<>();
            tmp.add(new Tiles(-1,1,1));
            tmp.add(new Tiles(-1,4,4));
        assertFalse(player.pickUp(playground,1,tmp));
    }
    @AfterEach
    public void reset(){
        player = null;
        playground = null;
    }
}
