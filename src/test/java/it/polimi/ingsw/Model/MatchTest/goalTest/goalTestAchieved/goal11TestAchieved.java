package it.polimi.ingsw.Model.MatchTest.goalTest.goalTestAchieved;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.CommonStrategy.GoalC11;

import it.polimi.ingsw.Model.CommonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class goal11TestAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC11());
    private Library library = new Library();
    private Library library1 = new Library();
    private Library library2 = new Library();
    private Library library3 = new Library();

    @Before
    public void setUp() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                library.getShelf()[i][j] = new Tiles(-1);
            }
        }
        // test principal diagonal ok
        library.getShelf()[0][0].setType(2);
        library.getShelf()[1][1].setType(2);
        library.getShelf()[2][2].setType(2);
        library.getShelf()[3][3].setType(2);
        library.getShelf()[4][4].setType(2);

        //test secondary diagonal ok
        library1.getShelf()[0][4].setType(2);
        library1.getShelf()[3][1].setType(2);
        library1.getShelf()[3][2].setType(2);
        library1.getShelf()[1][3].setType(2);
        library1.getShelf()[0][4].setType(2);

        //check rossa
        library2.getShelf()[1][0].setType(5);
        library2.getShelf()[2][1].setType(5);
        library2.getShelf()[3][2].setType(5);
        library2.getShelf()[4][3].setType(5);
        library2.getShelf()[5][4].setType(5);

        //check gialla
        library3.getShelf()[1][4].setType(2);
        library3.getShelf()[2][3].setType(2);
        library3.getShelf()[3][2].setType(2);
        library3.getShelf()[4][1].setType(2);
        library3.getShelf()[5][0].setType(2);




        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(library1.getShelf()[i][j].getType()+ "\t");
            }
            System.out.println();
        }



    }
    @Test
    public void validateGoal11a(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));
    }
    @Test
    public void validateGoal11b(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library1)));
    }
    @Test
    public void validateGoal11c(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library2)));
    }
    @Test
    public void validateGoal11d(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library3)));
    }

}
