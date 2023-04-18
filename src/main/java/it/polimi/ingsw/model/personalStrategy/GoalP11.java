package it.polimi.ingsw.model.personalStrategy;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Player;

import java.util.Map;
import java.util.Vector;

import static it.polimi.ingsw.model.Playground.Tiles.*;
import static it.polimi.ingsw.model.Playground.Tiles.YELLOW;

public class GoalP11 implements PersonalObj{
    private final Map<Integer,Integer> pointMap = Map.of(1,1,2,2,3,4,4,6,5,9,6,12);

    private final Vector<Tiles> position = new Vector<>();
    public GoalP11(){

        int[] x = new int[] {0,1,2,3,4,5};
        int[] y = new int[] {2,1,0,2,4,3};
        int[] type = new int[] {PINK,WHITE,YELLOW,BLUE,GREEN,CYAN};
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
}
