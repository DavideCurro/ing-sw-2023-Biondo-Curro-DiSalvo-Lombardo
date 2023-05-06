package it.polimi.ingsw.Model.MatchTest.goalTest.goalTestAchieved;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.CommonStrategy.GoalC6;
import it.polimi.ingsw.Model.CommonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class goal6TestAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC6());
    private Library library = new Library();
    @Before
    public void setUp(){
        Random random = new Random();
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 6; i++) {
                library.getShelf()[i][j] = new Tiles(random.nextInt(5));
            }
            library.getShelf()[1][j].setType(j);
            library.getShelf()[4][j].setType(j);

        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {

                System.out.print(library.getShelf()[i][j].getType()+ "\t");
            }
            System.out.println();
        }

    }

    @Test
    public void validateGoal6(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));

    }
}
