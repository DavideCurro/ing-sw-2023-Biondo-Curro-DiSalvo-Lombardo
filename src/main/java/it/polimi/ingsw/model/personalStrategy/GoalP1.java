package it.polimi.ingsw.model.personalStrategy;

import it.polimi.ingsw.Coordinate;
import it.polimi.ingsw.Player;
import java.util.Map;


/*
* Blue 0
* Light blue 1
* Yellow 2
* White 3
* Green 4
* Pink 5 */
public class GoalP1 {

    Map<Integer,Integer> pointMap = Map.of(1,1,2,2,3,4,4,6,5,9,6,12);


    public void setCard(){

        int[] x = new int[] {0,2,4,3,1,2};
        int[] y = new int[] {0,0,1,2,3,5};
        int[] type = new int[] {5,0,4,3,2,1};

        Coordinate c = new Coordinate();
        try{
            c.bulkADD(x, y, type);
        }catch(Exception e){
            System.out.println("Error:"+e);
        }


}


    public int check(Player p){
        int count = 0;
        for(int i =0; i<5; i++){
            if (p.getMy_shelfie().getShelf(([c.getX().get(i)][c.getY().get(i)]).getType(i)) == c.getType(i))
                count++;
        }
        int points = pointMap.get(count);
        //System.out.println("you won"+points+" points");

        return points;
    }
}
