package it.polimi.ingsw.model.PlaygroundTest;

import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.exception.CoordinateStateException;
import it.polimi.ingsw.model.exception.PlaygroundException;
import it.polimi.ingsw.model.player.Player;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class PlaygroundTest{
    Player player = new Player();
    Playground p = new Playground(3);

    public PlaygroundTest() throws PlaygroundException {
    }

    @Before

    /**
     * Rigourous Test :-)
     */

    @Test
    public void validateRefill() throws PlaygroundException {
        Tiles[][] tmp = new Tiles[9][9];
        for(int i=0;i<9;i++){
            for(int j = 0;j<9 ; j++){
                tmp[i][j]= new Tiles(-1);
            }
        }
        p.setGround(tmp);
        p.countSelected();
        assertTrue(assertEqualsGround(p.getGround(),tmp));
    }

    private boolean assertEqualsGround(Tiles[][] ground, Tiles[][] tmp) {
        for(int i = 0;i<9;i++){
            for(int j=0;j<9;j++){
                if(ground[i][j]!=tmp[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void validateValidTiles(){
        Vector<Tiles> tiles = new Vector<>();
        tiles.add(new Tiles(1,2,3));
        tiles.add(new Tiles(2,2,4));
        tiles.add(new Tiles(2,3,4));

        assertTrue(p.adjacency(tiles));
    }

}
