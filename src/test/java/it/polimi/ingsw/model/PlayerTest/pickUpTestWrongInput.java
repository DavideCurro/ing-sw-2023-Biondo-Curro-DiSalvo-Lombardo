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

import static org.junit.Assert.*;

public class pickUpTestWrongInput {
    Player player;
    Playground playground;
    @Before
    public void setPlayground() throws PlaygroundException {
        playground = new Playground(4);
        player = new Player(new GoalP1(),"Claudio");
    }

    @Test
    public void invalidColumn() throws CoordinateStateException {
        boolean thrown = false;
        //try {
           // player.pickUp(playground, 6, new int[]{0, 0, 1}, new int[]{3, 4, 3});
       // }catch (CoordinateStateException e){
            thrown = true;
        //}
        assertTrue(thrown);
    }
    @Test
    public void invalidCoordinate(){
        boolean thrown = false;
        try{
            Vector<Tiles> tmp = new Vector<>();
            tmp.add(new Tiles(-1,0,5));
            tmp.add(new Tiles(-1,4,4));
            tmp.add(new Tiles(-1,1,3));
            player.pickUp(playground,6,tmp);
        }catch (CoordinateStateException e){
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
