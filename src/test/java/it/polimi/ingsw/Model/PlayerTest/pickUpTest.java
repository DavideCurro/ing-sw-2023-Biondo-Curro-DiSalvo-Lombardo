package it.polimi.ingsw.Model.PlayerTest;

import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.Exception.CoordinateStateException;
import it.polimi.ingsw.Model.Exception.PlaygroundException;
import it.polimi.ingsw.Model.PersonalStrategy.GoalP1;
import it.polimi.ingsw.Model.Player.Player;
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
