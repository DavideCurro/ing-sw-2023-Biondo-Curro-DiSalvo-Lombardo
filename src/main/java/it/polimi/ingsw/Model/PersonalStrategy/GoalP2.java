package it.polimi.ingsw.Model.PersonalStrategy;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.Player.Player;

import static it.polimi.ingsw.Model.Playground.Tiles.*;

import java.io.Serializable;
import java.util.Map;
import java.util.Vector;


/*
 * Blue 0
 * Light blue 1
 * Yellow 2
 * White 3
 * Green 4
 * Pink 5 */

public class GoalP2 implements PersonalObj, Serializable {

    private final Map<Integer,Integer> pointMap = Map.of(1,1,2,2,3,4,4,6,5,9,6,12);

    private final Vector<Tiles> position = new Vector<>();
    public GoalP2(){

        int[] x = new int[] {2,1,2,3,4,5}; //rows
        int[] y = new int[] {0,1,2,4,3,4}; //columns
        int[] type = new int[] {GREEN,PINK,YELLOW,WHITE,CYAN,BLUE};
        for(int i = 0; i < x.length; i++){
            this.position.add(new Tiles(type[i],x[i],y[i]));
        }


    }


    public int check(Player p){
        int count = 0;
        int [] coordinate;
        for(Tiles tmp : position){
            coordinate = tmp.getXYType();
            if (p.getMy_shelfie().getShelf()[coordinate[0]][coordinate[1]].getType() == coordinate[2]) //you can also use all the get, by following le previous line code
                count++;
        }
        //System.out.println("you won"+points+" points");
        if(count == 0) return 0;
        return pointMap.get(count);
    }
    @Override
    public Vector<Tiles> getPosition() {
        return position;
    }
}
