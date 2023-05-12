package it.polimi.ingsw.Model.PlayerTest;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.PersonalStrategy.*;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class personalOBJTest {
    Player player;
    private PersonalObj pickPersonalObj(int choose){
        return switch (choose) {
            case 1 -> new GoalP1();
            case 2 -> new GoalP2();
            case 3 -> new GoalP3();
            case 4 -> new GoalP4();
            case 5 -> new GoalP5();
            case 6 -> new GoalP6();
            case 7 -> new GoalP7();
            case 8 -> new GoalP8();
            case 9 -> new GoalP9();
            case 10 -> new GoalP10();
            case 11 -> new GoalP11();
            case 12 -> new GoalP12();

            default -> throw new IllegalStateException("Unexpected value: " );
        };
    }
    @Before
    public void setOBJ(){
        player = new Player(new GoalP1(),"Claudio");
        Tiles[][] tmp = new Tiles[6][5];
        Vector<Tiles> coordinate = new GoalP1().getPosition();
        for (Tiles tile : coordinate){
            tmp[tile.getX()][tile.getY()] = new Tiles(tile.getType(),tile.getX(),tile.getY());
        }
        Library library = new Library(tmp);
        player.setMy_shelfie(library);
    }
    @Test
    public void validatePersonalOBJ(){

        int point = player.checkPersonalOBJ();
        player.setPrivatePoints(point);
        assertEquals(12,player.getPrivatePoints());
        for(int i = 2; i<= 12;i++){
            PersonalObj personalObj = pickPersonalObj(i);
            player = new Player(personalObj,"c");
            Tiles[][] tmp = new Tiles[6][5];
            Vector<Tiles> coordinate = personalObj.getPosition();
            for (Tiles tile : coordinate){
                tmp[tile.getX()][tile.getY()] = new Tiles(tile.getType(),tile.getX(),tile.getY());
            }
            Library library = new Library(tmp);
            player.setMy_shelfie(library);
            point = player.checkPersonalOBJ();
            player.setPrivatePoints(point);
            assertEquals(12,player.getPrivatePoints());
        }
    }
}
