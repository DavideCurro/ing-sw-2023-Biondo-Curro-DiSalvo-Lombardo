package it.polimi.ingsw.Model.PlayerTest;

import it.polimi.ingsw.Model.Exception.CoordinateStateException;
import it.polimi.ingsw.Model.Exception.PlaygroundException;
import it.polimi.ingsw.Model.PersonalStrategy.GoalP1;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.Vector;

import static org.junit.Assert.assertFalse;

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
