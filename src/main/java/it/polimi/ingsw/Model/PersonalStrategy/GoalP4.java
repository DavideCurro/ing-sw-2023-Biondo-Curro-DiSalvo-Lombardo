package it.polimi.ingsw.Model.PersonalStrategy;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.Player.Player;

import static it.polimi.ingsw.Model.Playground.Tiles.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


/*
 * Blue 0
 * Light blue 1
 * Yellow 2
 * White 3
 * Green 4
 * Pink 5 */
public class GoalP4 implements PersonalObj, Serializable {

    private final Map<Integer,Integer> pointMap = Map.of(1,1,2,2,3,4,4,6,5,9,6,12);
    private HashMap<Tiles, Boolean> test = new HashMap<>();
    private final int type = 4;

    private final Vector<Tiles> position = new Vector<>();
    public GoalP4(){

        int[] x = new int[] {0,2,2,3,4,4}; //rows
        int[] y = new int[] {4,0,2,3,1,2}; //columns
        int[] type = new int[] {YELLOW,CYAN,BLUE,PINK,WHITE,GREEN};
        for(int i = 0; i < x.length; i++){
            this.position.add(new Tiles(type[i],x[i],y[i]));
            this.test.put(new Tiles(type[i],x[i],y[i]) , false);
        }


    }


    public int check(Player p){
        int count = 0;
        int [] coordinate;
        for(Tiles tmp : test.keySet()){
            coordinate = tmp.getXYType();
            if(test.get(tmp)) continue;
            if (p.getMy_shelfie().getShelf()[coordinate[0]][coordinate[1]].getType() == coordinate[2]) { //you can also use all the get, by following le previous line code
                count++;
                test.replace(tmp,true);
            }
        }
        //System.out.println("you won"+points+" points");
        if(count == 0 ) return 0;
        return pointMap.get(count);
    }
    @Override
    public Vector<Tiles> getPosition() {
        return position;
    }
    public int getType(){return type;}

}

