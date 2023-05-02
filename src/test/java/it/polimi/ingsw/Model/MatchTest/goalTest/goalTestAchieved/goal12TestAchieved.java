package it.polimi.ingsw.Model.MatchTest.goalTest.goalTestAchieved;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.CommonStrategy.GoalC12;

import it.polimi.ingsw.Model.CommonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class goal12TestAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC12());
    private Library library = new Library();
    private Library library2 = new Library();

    @Before
    public void setUp() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                library.getShelf()[i][j] = new Tiles(-1);
                library2.getShelf()[i][j] = new Tiles(-1);


            }
            library.getShelf()[i][0].setType(5);
            library2.getShelf()[i][4].setType(5);

        }

        library.getShelf()[0][0].setType(5);
        library.getShelf()[1][1].setType(5);
        library.getShelf()[2][2].setType(5);
        library.getShelf()[3][3].setType(5);
        library.getShelf()[4][4].setType(5);

        library2.getShelf()[0][4].setType(5);
        library2.getShelf()[1][3].setType(5);
        library2.getShelf()[2][2].setType(5);
        library2.getShelf()[3][1].setType(5);
        library2.getShelf()[4][0].setType(5);
       // library.getShelf()[0][1].setType(5);


        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(library.getShelf()[i][j].getType()+ "\t");
            }
            System.out.println();
        }

        System.out.println("");

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(library2.getShelf()[i][j].getType()+ "\t");

            }
            System.out.println();
        }

    }
    @Test
    public void validateGoal12a(){

        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));
    }
    @Test
    public void validateGoal12b(){

        assertTrue(objectiveCommonEXEC.execCheck(new Player(library2)));
    }

}

