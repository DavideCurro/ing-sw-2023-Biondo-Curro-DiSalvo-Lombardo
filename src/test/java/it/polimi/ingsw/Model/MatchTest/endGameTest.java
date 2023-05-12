package it.polimi.ingsw.Model.MatchTest;

import it.polimi.ingsw.Client.Client;
import it.polimi.ingsw.Client.ClientView;
import it.polimi.ingsw.Controller.Match;
import it.polimi.ingsw.Controller.MatchExeception;
import it.polimi.ingsw.Model.CommonStrategy.GoalC1;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Tiles;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class endGameTest {
    Player player;
    ClientView view = new ClientView();
    @Before
    public void setUP() throws MatchExeception {

        player = new Player(setLibrary());
        view.printPlayerLibrary(player);
    }
    public Library setLibrary(){
        Library library = new Library();
        Random random = new Random();
        for(int i = 0; i<6;i++){
            for(int j = 0; j <5; j++){
                library.getShelf()[i][j]=new Tiles(-1,i,j);
            }

        }
        library.getShelf()[2][2].setType(0);
        library.getShelf()[2][3].setType(0);
        library.getShelf()[3][2].setType(0);
        library.getShelf()[3][3].setType(0);
        return library;
    }
    @Test
    public void testEndGame(){
       Vector<Integer> tmp = player.calculateADJ();
       int point = tmp.get(0);
        assertEquals(4,point);
    }
}
