package it.polimi.ingsw.model.MatchTest.goalTest.goalTestNotAchieved;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.commonStrategy.GoalC5;
import it.polimi.ingsw.model.commonStrategy.GoalC6;
import it.polimi.ingsw.model.commonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class goal6TestNotAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC6());
    private Library library = new Library();
    @Before
    public void setUp(){
        Random random = new Random();
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 6; i++) {
                library.getShelf()[i][j] = new Tiles(1);
            }

        }

    }
    @Test
    public void validateGoal6(){
        assertFalse(objectiveCommonEXEC.execCheck(new Player(library)));

    }
}
