package it.polimi.ingsw.Model.PersonalStrategy;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.Player.Player;

import static it.polimi.ingsw.Model.Playground.Tiles.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class GoalP9 implements PersonalObj, Serializable {
    private final Map<Integer,Integer> pointMap = Map.of(1,1,2,2,3,4,4,6,5,9,6,12);
    private HashMap<Tiles, Boolean> test = new HashMap<>();
    private final int type = 9;

    private final Vector<Tiles> position = new Vector<>();
    public GoalP9(){

        int[] x = new int[] {0,4,2,3,4,5};
        int[] y = new int[] {2,1,2,4,4,0};
        int[] type = new int[] {YELLOW,CYAN,GREEN,WHITE,PINK,BLUE};
        for(int i = 0; i < x.length; i++){
            this.position.add(new Tiles(type[i],x[i],y[i]));
            this.test.put(new Tiles(type[i],x[i],y[i]) , false);
        }


    }

    @Override
    public Vector<Tiles> getPosition() {
        return position;
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
    public int getType(){return type;}

}
