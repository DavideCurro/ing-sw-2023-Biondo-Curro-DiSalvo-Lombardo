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
public class GoalP3 implements PersonalObj, Serializable {

    private final Map<Integer,Integer> pointMap = Map.of(1,1,2,2,3,4,4,6,5,9,6,12);
    private final int type = 3;

    private final Vector<Tiles> position = new Vector<>();
    public GoalP3(){

        int[] x = new int[] {1,1,2,3,3,5}; //rows
        int[] y = new int[] {0,3,2,1,4,0}; //columns
        int[] type = new int[] {BLUE,YELLOW,PINK,GREEN,CYAN,WHITE};
        for(int i = 0; i < x.length; i++){
            this.position.add(new Tiles(type[i],x[i],y[i]));
        }


    }
    @Override
    public Vector<Tiles> getPosition() {
        return position;
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
    public int getType(){return type;}

}

