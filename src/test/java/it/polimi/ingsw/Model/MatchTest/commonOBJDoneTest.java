package it.polimi.ingsw.Model.MatchTest;

import it.polimi.ingsw.Controller.Match;
import it.polimi.ingsw.Controller.MatchExeception;
import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.CommonStrategy.GoalC1;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class commonOBJDoneTest {
    private Match match;
    LinkedList<Player> players = new LinkedList<>();
    private final Library library = new Library();
    @Before
    public void setUP() throws MatchExeception {
        match = new Match();

        players.add(new Player(match.personalOBJChooser(),"c"));
        players.add((new Player(match.personalOBJChooser(),"s")));
        players.add((new Player(match.personalOBJChooser(),"m")));
        match = new Match(players, new GoalC1(),new GoalC1());
        match.setupPlayground();
        setLibrary();
    }

    @Before
    public void setLibrary(){
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
    public void validateCommonOBJ() throws MatchExeception {
        Match.pointSetter(1,players.get(0));
        players.get(0).setPoints(0);
        assertEquals(8,players.get(0).getPoints());
        Match.pointSetter(2,players.get(1));
        players.get(1).setPoints(0);
        assertEquals(6,players.get(1).getPoints());

        Match.pointSetter(3,players.get(2));
        players.get(2).setPoints(0);
        assertEquals(4,players.get(2).getPoints());
    }
}
