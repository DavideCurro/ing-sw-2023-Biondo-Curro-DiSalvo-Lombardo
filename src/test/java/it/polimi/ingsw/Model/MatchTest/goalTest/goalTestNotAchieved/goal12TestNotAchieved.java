package it.polimi.ingsw.Model.MatchTest.goalTest.goalTestNotAchieved;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.CommonStrategy.GoalC12;
import it.polimi.ingsw.Model.CommonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class goal12TestNotAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC12());
    private Library library = new Library();

    @Before
    public void setUp() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                library.getShelf()[i][j] = new Tiles(-1);

            }
            library.getShelf()[i][0].setType(5);
        }

        library.getShelf()[0][0].setType(5);
        library.getShelf()[1][1].setType(5);
        library.getShelf()[2][2].setType(5);
        library.getShelf()[3][3].setType(5);
        library.getShelf()[4][4].setType(5);
        library.getShelf()[0][1].setType(5);

        library.getShelf()[1][4].setType(5);
        library.getShelf()[1][4].setType(5);
        library.getShelf()[2][2].setType(5);
        library.getShelf()[3][3].setType(5);
        library.getShelf()[4][4].setType(5);
        library.getShelf()[0][1].setType(5);





        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(library.getShelf()[i][j].getType()+ "\t");
            }
            System.out.println();
        }

    }
    @Test
    public void validateGoal12(){

        assertFalse(objectiveCommonEXEC.execCheck(new Player(library)));
    }

}
