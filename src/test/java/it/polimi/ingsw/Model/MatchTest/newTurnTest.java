package it.polimi.ingsw.Model.MatchTest;

import it.polimi.ingsw.Controller.Match;
import it.polimi.ingsw.Controller.MatchExeception;
import it.polimi.ingsw.Model.CommonStrategy.*;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Tiles;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class newTurnTest {
    Match match;

    @Before
    public void setUP() throws MatchExeception {
        match = new Match();
        LinkedList<Player> players = new LinkedList<>();
        players.add(new Player(match.personalOBJChooser(),"c"));
        players.add((new Player(match.personalOBJChooser(),"s")));
        match = new Match(players, new GoalC12(),new GoalC12());
        match.setupPlayground();
    }

    @Test
    public void validateNewTurnTest() throws MatchExeception {
        Vector<Tiles> tmp = new Vector<>();
        tmp.add(new Tiles(-1,1,3));
        tmp.add(new Tiles(-1,1,4));
        assertEquals(0,match.newTurn(1, tmp));
    }
}
