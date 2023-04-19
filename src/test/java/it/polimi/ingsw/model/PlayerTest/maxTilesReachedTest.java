package it.polimi.ingsw.model.PlayerTest;

import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.exception.CoordinateStateException;
import it.polimi.ingsw.model.exception.PlaygroundException;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class maxTilesReachedTest {
    Player player;
    Playground playground;
    @Before
    public void setUp() throws PlaygroundException {
        playground = new Playground(2);
        player = new Player();
        for(int i = 0;i<6;i++){
             player.getMy_shelfie().getShelf()[i][1].setType(1);
        }
    }
    @Test
    public void maxTilesReached(){
        boolean thrown = false;
        try{
            player.pickUp(playground,1,new int[]{1,1,1},new int[]{1,2,3});
        }catch (Exception e ){
            thrown = true;
        }

        assertTrue(thrown);
    }
    private void setUpLibrary(){
        for(int i =0; i<4;i++) {
            player.getMy_shelfie().getShelf()[i][2].setType(1);
        }
    }
    @Test
    public void validateMaxTiles(){
        setUpLibrary();
        assertEquals(2,player.calculateMaxTiles(2,3));
    }
}
