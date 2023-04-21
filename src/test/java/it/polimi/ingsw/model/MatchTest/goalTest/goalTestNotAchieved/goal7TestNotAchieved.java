package it.polimi.ingsw.model.MatchTest.goalTest.goalTestNotAchieved;

import it.polimi.ingsw.model.Playground.Tiles;

import it.polimi.ingsw.model.commonStrategy.GoalC7;
import it.polimi.ingsw.model.commonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;

public class goal7TestNotAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC7());
    private Library library = new Library();
    @Before
    public void setUp(){
        Random random = new Random();
        for(int i = 0;i<6; i++){
            for(int j = 0;j<5;j++){
                library.getShelf()[i][1] = new Tiles(random.nextInt(3));
                library.getShelf()[i][3] = new Tiles(random.nextInt(3));
                library.getShelf()[i][4] = new Tiles(random.nextInt(5));
                library.getShelf()[i][2] = new Tiles(random.nextInt(5));
                library.getShelf()[i][0] = new Tiles(random.nextInt(5));
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
    public void validateGoal7(){
        assertFalse(objectiveCommonEXEC.execCheck(new Player(library)));

    }
}
