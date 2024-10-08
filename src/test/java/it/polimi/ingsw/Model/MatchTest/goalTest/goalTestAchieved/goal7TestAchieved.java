package it.polimi.ingsw.Model.MatchTest.goalTest.goalTestAchieved;

import it.polimi.ingsw.Model.Playground.Tiles;

import it.polimi.ingsw.Model.CommonStrategy.GoalC7;
import it.polimi.ingsw.Model.CommonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class goal7TestAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC7());
    private Library library = new Library();
    @Before
    public void setUp(){
        Random random = new Random();
        for(int i = 0;i<6; i++){
            library.getShelf()[i][1] = new Tiles(random.nextInt(3));
            library.getShelf()[i][3] = new Tiles(random.nextInt(3));
            library.getShelf()[i][4] = new Tiles(random.nextInt(3));
            library.getShelf()[i][2] = new Tiles(random.nextInt(3));
            library.getShelf()[i][0] = new Tiles(random.nextInt(5));

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
        objectiveCommonEXEC.getType();
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));

    }
}
