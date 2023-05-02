package it.polimi.ingsw.Model.MatchTest.goalTest.goalTestAchieved;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.CommonStrategy.GoalC3;
import it.polimi.ingsw.Model.CommonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class goal3TestAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC3());
    private Library library = new Library();
    @Before
    public void setUp(){
        Random random = new Random();
        for(int i = 0; i<6;i++){
            for(int j = 0; j <5; j++){
                library.getShelf()[i][j]=new Tiles(5,i,j);
            }
        }
        for(int i = 1;i<5;i++){
            library.getShelf()[i][1].setType(1);
            library.getShelf()[i-1][4].setType(1);
        }
        for(int j = 0; j<4;j++){
            library.getShelf()[2][j].setType(1);
            library.getShelf()[5][j].setType(1);
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {

                System.out.print(library.getShelf()[i][j].getType()+ "\t");
            }
            System.out.println();
        }


    }
    @Test
    public void validateGoal3(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));

    }
}
