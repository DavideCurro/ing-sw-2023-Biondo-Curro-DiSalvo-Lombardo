package it.polimi.ingsw.model.MatchTest.goalTest.goalTestAchieved;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.commonStrategy.GoalC8;
import it.polimi.ingsw.model.commonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class goal8TestAchievied {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC8());
    private Library library = new Library();

    @Before
    public void setUp(){

        for(int i = 0; i<6;i++){
            for(int j = 0; j <5; j++){
                library.getShelf()[i][j]=new Tiles(-1);
            }
        }
        library.getShelf()[0][0].setType(5);
        library.getShelf()[5][0].setType(5);

        library.getShelf()[0][4].setType(5);
        library.getShelf()[5][4].setType(5);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(library.getShelf()[i][j].getType()+ "\t");
            }
            System.out.println();
        }

    }
    @Test
    public void validateGoal1(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));
    }


}
