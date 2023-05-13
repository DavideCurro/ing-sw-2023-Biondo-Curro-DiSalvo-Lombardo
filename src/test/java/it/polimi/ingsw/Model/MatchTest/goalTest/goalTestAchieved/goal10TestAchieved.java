package it.polimi.ingsw.Model.MatchTest.goalTest.goalTestAchieved;
import it.polimi.ingsw.Model.CommonStrategy.GoalC10;
import it.polimi.ingsw.Model.CommonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Tiles;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class goal10TestAchieved {

    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC10());
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
        library.getShelf()[1][1].setType(2);
        library.getShelf()[2][2].setType(2);
        library.getShelf()[1][3].setType(2);
        library.getShelf()[3][3].setType(2);
        library.getShelf()[3][1].setType(2);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(library.getShelf()[i][j].getType() + "\t");
            }
            System.out.println();
        }
    }
    @Test
    public void validateGoal10(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));
    }

}
