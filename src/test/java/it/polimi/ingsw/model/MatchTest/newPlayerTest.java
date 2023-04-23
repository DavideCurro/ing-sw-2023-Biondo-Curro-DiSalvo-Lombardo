package it.polimi.ingsw.model.MatchTest;

import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.controller.MatchExeception;
import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.exception.CoordinateStateException;
import it.polimi.ingsw.model.exception.PlaygroundException;
import it.polimi.ingsw.model.personalStrategy.GoalP1;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

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
