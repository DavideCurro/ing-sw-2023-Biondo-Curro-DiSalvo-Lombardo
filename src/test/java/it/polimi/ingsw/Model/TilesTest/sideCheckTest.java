package it.polimi.ingsw.Model.TilesTest;

import it.polimi.ingsw.Model.Playground.Tiles;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.assertTrue;

public class sideCheckTest {
    Vector<Tiles> tile ;
    @Before
    public void setUP(){
        tile = new Vector<>();
        tile.add(new Tiles(1,1,1));
        tile.add(new Tiles(1,2,1));
        tile.add(new Tiles(1,0,1));
        tile.add(new Tiles(1,2,2));
    }
    @Test
    public void allSideChecksTest(){
        boolean done = false;
        for (int i = 0;i<tile.size();i++) {
            if(!done)
                for(int j = i; j< tile.size();j ++){
                    if(j == i)  continue;
                    done = tile.get(j).checkSides(tile, j,tile.get(i).getX(),tile.get(i).getY());
                }
            else break;
        }
        assertTrue(done);
    }
}
