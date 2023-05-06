package it.polimi.ingsw.Model.MatchTest;

import it.polimi.ingsw.Controller.Match;
import it.polimi.ingsw.Controller.MatchExeception;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class newPlayerTest {
    Match match;

    @Before
    public void setUP(){
        match = new Match();
    }

    @Test
    public void validateNewPlayer() throws MatchExeception {
        match.newPlayer("c");
        Player tmp = new Player();
        tmp.setNickname("c");
        assertEquals(tmp.getNickname(),match.getLastPlayer().getNickname());
        match.newPlayer("a");
        tmp.setNickname("a");
        assertEquals(tmp.getNickname(),match.getLastPlayer().getNickname());
    }


}
