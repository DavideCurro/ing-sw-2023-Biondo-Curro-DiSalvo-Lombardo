package it.polimi.ingsw.model.MatchTest.goalTest.goalTestAchieved;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.commonStrategy.GoalC1;
import it.polimi.ingsw.model.commonStrategy.GoalC4;
import it.polimi.ingsw.model.commonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class goal4TestAchieved{
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC4());
    private Library library = new Library();
    @Before
    public void setUp(){
        Random random = new Random();
        for(int i = 0; i<6;i++){
            for(int j = 0; j <5; j++){
                library.getShelf()[i][j]=new Tiles(random.nextInt(5),i,j);
            }
        }
        library.getShelf()[1][1].setType(1);
        library.getShelf()[1][2].setType(1);

        library.getShelf()[3][3].setType(1);
        library.getShelf()[4][3].setType(1);

        library.getShelf()[2][2].setType(1);
        library.getShelf()[2][3].setType(1);

        library.getShelf()[4][4].setType(2);
        library.getShelf()[3][4].setType(2);

        library.getShelf()[5][4].setType(1);
        library.getShelf()[5][3].setType(1);

        library.getShelf()[0][0].setType(2);
        library.getShelf()[1][0].setType(2);

    }
    @Test
    public void validateGoal4(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));

    }
}
