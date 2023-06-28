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
public class GoalP1 implements PersonalObj, Serializable {

    private final Map<Integer,Integer> pointMap = Map.of(1,1,2,2,3,4,4,6,5,9,6,12);
    private final int type = 1;
    private HashMap<Tiles, Boolean> test = new HashMap<>();
    private Vector<Tiles> posix = new Vector<>(5);
    public GoalP1(){
        int[] x = new int[] {0,0,1,2,3,5}; //rows
        int[] y = new int[] {0,2,4,3,1,2}; //columns
        int[] type = new int[] {PINK,BLUE,GREEN,WHITE,YELLOW,CYAN};
        for(int i = 0; i < x.length; i++){
            this.posix.add(new Tiles(type[i],x[i],y[i]));
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
    public Vector<Tiles> getPosition(){
        return posix;
    }
    public int getType(){return type;}
}
