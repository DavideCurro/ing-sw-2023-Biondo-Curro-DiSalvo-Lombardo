package it.polimi.ingsw.model.PlayerTest;

import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.exception.CoordinateStateException;
import it.polimi.ingsw.model.exception.PlaygroundException;
import it.polimi.ingsw.model.personalStrategy.GoalP1;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

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
    public void alreadyPickedTest(){
        boolean thrown = false;
        try{
            player.pickUp(playground,1,new int[]{0},new int[]{0});
        }catch (Exception e){
            thrown = true;

        }
        assertTrue( thrown);
    }
    @Test
    public void notAdjacentTest(){
        boolean thrown = false;
        try {
            player.pickUp(playground,1,new int[]{1,4},new int[]{1,4});
        }catch (Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }
    @AfterEach
    public void reset(){
        player = null;
        playground = null;
    }
}
