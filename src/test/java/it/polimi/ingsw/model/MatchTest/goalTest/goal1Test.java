package it.polimi.ingsw.model.MatchTest.goalTest;

import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.controller.MatchExeception;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.commonStrategy.GoalC1;
import it.polimi.ingsw.model.commonStrategy.ObjectiveCommonEXEC;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class goal1Test {
    private ObjectiveCommonEXEC objectiveCommonEXEC = new ObjectiveCommonEXEC(new GoalC1());
    private Library library = new Library();
    @Before
    public void setUp(){
        for(int i = 0; i<6;i++){
            for(int j = 0; j <5; j++){
                library.getShelf()[i][j]=new Tiles(-1);
            }

        }
        library.getShelf()[2][2].setType(5);
        library.getShelf()[2][3].setType(5);

        library.getShelf()[4][2].setType(5);
        library.getShelf()[4][3].setType(5);

        library.getShelf()[1][2].setType(5);
        library.getShelf()[1][1].setType(5);

        library.getShelf()[0][0].setType(5);
        library.getShelf()[0][1].setType(5);

        library.getShelf()[1][5].setType(5);
        library.getShelf()[1][6].setType(5);

        library.getShelf()[5][1].setType(5);
        library.getShelf()[5][2].setType(5);

        library.getShelf()[3][3].setType(5);
        library.getShelf()[3][4].setType(5);
    }
    @Test
    public void validateGoal1(){
        assertTrue(objectiveCommonEXEC.execCheck(new Player(library)));

    }
}
