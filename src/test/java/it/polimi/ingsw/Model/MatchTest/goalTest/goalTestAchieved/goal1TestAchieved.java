package it.polimi.ingsw.Model.MatchTest.goalTest.goalTestAchieved;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.CommonStrategy.GoalC1;
import it.polimi.ingsw.Model.CommonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class goal1TestAchieved {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC1());
    private Library library = new Library();
    @Before
    public void setUp(){
        Random random = new Random();
        for(int i = 0; i<6;i++){
            for(int j = 0; j <5; j++){
                library.getShelf()[i][j]=new Tiles(random.nextInt(5),i,j);
            }

        }
        library.getShelf()[2][2].setType(5);
        library.getShelf()[2][3].setType(5);
        library.getShelf()[3][2].setType(5);
        library.getShelf()[3][3].setType(5);

        library.getShelf()[0][3].setType(5);
        library.getShelf()[0][4].setType(5);
        library.getShelf()[1][3].setType(5);
        library.getShelf()[1][4].setType(5);

    }
    @Test
    public void validateGoal1(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));

    }
}
