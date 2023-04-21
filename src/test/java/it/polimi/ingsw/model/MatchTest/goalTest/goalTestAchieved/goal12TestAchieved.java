package it.polimi.ingsw.model.MatchTest.goalTest.goalTestAchieved;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.commonStrategy.GoalC12;

import it.polimi.ingsw.model.commonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class goal12TestAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC12());
    private Library library = new Library();

    @Before
    public void setUp() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                library.getShelf()[i][j] = new Tiles(-1);
                library.getShelf()[i][j].setType(2);
            }
        }
        for(int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                library.getShelf()[i][j].setType(-1);
            }
        }
        library.getShelf()[0][1].setType(1);
        library.getShelf()[0][4].setType(1);
        library.getShelf()[1][0].setType(1);
        library.getShelf()[2][2].setType(1);
        library.getShelf()[3][3].setType(1);
        library.getShelf()[4][1].setType(1);
        library.getShelf()[5][0].setType(1);
        library.getShelf()[5][4].setType(1);


        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(library.getShelf()[i][j].getType()+ "\t");
            }
            System.out.println();
        }

    }
    @Test
    public void validateGoal12(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));
    }

}

