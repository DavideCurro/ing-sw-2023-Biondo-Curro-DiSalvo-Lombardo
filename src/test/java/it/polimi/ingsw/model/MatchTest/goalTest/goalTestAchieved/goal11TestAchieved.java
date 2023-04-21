package it.polimi.ingsw.model.MatchTest.goalTest.goalTestAchieved;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.commonStrategy.GoalC11;
import it.polimi.ingsw.model.commonStrategy.GoalC9;
import it.polimi.ingsw.model.commonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class goal11TestAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC11());
    private Library library = new Library();

    @Before
    public void setUp() {
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                library.getShelf()[i][j] = new Tiles(-1);
            }
        }
        // test principal diagonal
        library.getShelf()[0][0].setType(2);
        library.getShelf()[1][1].setType(2);
        library.getShelf()[2][2].setType(2);
        library.getShelf()[3][3].setType(2);
        library.getShelf()[4][4].setType(2);

        //test secondary diagonal
        library.getShelf()[0][4].setType(2);
        library.getShelf()[1][3].setType(2);
        library.getShelf()[2][2].setType(2);
        library.getShelf()[3][1].setType(2);
        library.getShelf()[4][0].setType(2);

        //



        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(library.getShelf()[i][j].getType()+ "\t");
            }
            System.out.println();
        }

    }
    @Test
    public void validateGoal11(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));
    }

}
