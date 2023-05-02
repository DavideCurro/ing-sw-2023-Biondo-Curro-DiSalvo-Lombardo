package it.polimi.ingsw.Model.MatchTest.goalTest.goalTestNotAchieved;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.CommonStrategy.GoalC4;
import it.polimi.ingsw.Model.CommonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;

public class goal4TestNotAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC4());
    private Library library = new Library();
    @Before
    public void setUp(){
        Random random = new Random();
        for(int i = 0; i<6;i++){
            for(int j = 0; j <5; j++){
                library.getShelf()[i][j]=new Tiles(random.nextInt(50)/(i+1),i,j);
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {

                System.out.print(library.getShelf()[i][j].getType()+ "\t");
            }
            System.out.println();
        }

    }
    @Test
    public void invalidateGoal4(){
        assertFalse(objectiveCommonEXEC.execCheck(new Player(library)));

    }
}
