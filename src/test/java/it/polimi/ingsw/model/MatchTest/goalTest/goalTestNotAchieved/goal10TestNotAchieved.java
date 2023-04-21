package it.polimi.ingsw.model.MatchTest.goalTest.goalTestNotAchieved;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.commonStrategy.GoalC10;
import it.polimi.ingsw.model.commonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertFalse;

public class goal10TestNotAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC10());
    private Library library = new Library();

    @Before
    public void setUp() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                library.getShelf()[i][j] = new Tiles(-1);
            }
        }
        library.getShelf()[1][0].setType(2);
        library.getShelf()[4][1].setType(4);
        library.getShelf()[1][2].setType(2);
        library.getShelf()[3][3].setType(3);
        library.getShelf()[2][4].setType(2);
       /* library.getShelf()[5][4].setType(2);
        library.getShelf()[3][2].setType(1);
        library.getShelf()[3][1].setType(2);
        library.getShelf()[2][1].setType(4);
        library.getShelf()[2][4].setType(2);
        library.getShelf()[5][3].setType(3);
        library.getShelf()[1][4].setType(2);
        library.getShelf()[5][0].setType(2);
        library.getShelf()[0][2].setType(1);
*/
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(library.getShelf()[i][j].getType() + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void validateGoal10(){
        assertFalse(objectiveCommonEXEC.execCheck(new Player(library)));
    }

}

