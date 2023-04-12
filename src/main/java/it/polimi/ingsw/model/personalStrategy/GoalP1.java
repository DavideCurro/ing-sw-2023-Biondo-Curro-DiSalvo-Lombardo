package it.polimi.ingsw.model.personalStrategy;

import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Player;

import static it.polimi.ingsw.model.Playground.Tiles.*;

import java.util.Map;
import java.util.Vector;


/*
* Blue 0
* Light blue 1
* Yellow 2
* White 3
* Green 4
* Pink 5 */
public class GoalP1 implements PersonalObj{

    Map<Integer,Integer> pointMap = Map.of(1,1,2,2,3,4,4,6,5,9,6,12);

    Vector<Tiles> position = new Vector<>();
    private void setCard(){

        int[] x = new int[] {0,2,4,3,1,2};
        int[] y = new int[] {0,0,1,2,3,5};
        int[] type = new int[] {PINK,BLUE,GREEN,WHITE,YELLOW,CYAN};
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
