package it.polimi.ingsw.model.personalStrategy;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Player;

import java.util.Map;
import java.util.Vector;

import static it.polimi.ingsw.model.Playground.Tiles.*;
import static it.polimi.ingsw.model.Playground.Tiles.YELLOW;

public class GoalP12 implements PersonalObj{
    Map<Integer,Integer> pointMap = Map.of(1,1,2,2,3,4,4,6,5,9,6,12);

    Vector<Tiles> position = new Vector<>();
    private void setCard(){

        int[] x = new int[] {0,1,2,3,4,5};
        int[] y = new int[] {2,1,2,3,4,0};
        int[] type = new int[] {WHITE,PINK,BLUE,CYAN,YELLOW,GREEN};
        for(int i = 0; i < x.length; i++){
            this.position.get(i).setALL(x[i],y[i],type[i]);
        }


    }


    public int check(Player p){
        setCard();
        int count = 0;
        int [] coordinate;
        for(int i =0; i<5; i++){
            coordinate = position.get(i).getXYType();
            if (p.getMy_shelfie().getShelf()[coordinate[0]][coordinate[1]].getType() == coordinate[2]) //you can also use all the get, by following le previous line code
                count++;
        }
        //System.out.println("you won"+points+" points");

        return pointMap.get(count);
    }
}
