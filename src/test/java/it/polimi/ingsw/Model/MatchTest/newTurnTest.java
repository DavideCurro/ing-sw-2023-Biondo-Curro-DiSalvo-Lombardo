package it.polimi.ingsw.Model.MatchTest;

import it.polimi.ingsw.Controller.Match;
import it.polimi.ingsw.Controller.MatchExeception;
import it.polimi.ingsw.Model.CommonStrategy.*;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class newTurnTest {
    Match match;

    @Before
    public void setUP() throws MatchExeception {
        match = new Match();
        LinkedList<Player> players = new LinkedList<>();
        players.add(new Player(match.personalOBJChooser(),"c"));
        players.add((new Player(match.personalOBJChooser(),"s")));
        match = new Match(players, new GoalC12());
        match.setupPlayground();
    }

    @Test
    public void validateNewTurnTest() throws MatchExeception {
       // assertEquals(0,match.newTurn(1, new int[]{1,1,2},new int[]{3,4,3}));
    }
}
