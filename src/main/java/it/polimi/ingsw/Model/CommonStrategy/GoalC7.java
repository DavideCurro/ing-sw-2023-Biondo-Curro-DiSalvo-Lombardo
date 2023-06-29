package it.polimi.ingsw.Model.CommonStrategy;

import java.io.Serializable;
import java.util.*;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Tiles;

import static it.polimi.ingsw.Model.Playground.Tiles.NOT_VALID;


public class GoalC7 implements CommonObj , Serializable {
    private final int type = 7;
    public int getType(){
        return type;
    }
    /** Four lines each formed by 5 tiles of maximum three different types.
     *  One line can show the same or a different combination of another line.
     *  @param p
     *  @return
     *  */

    public boolean check(Player p) {
        int count = 0;
        Vector<Tiles> riga0 = new Vector<>();
        for (int i = 5; i >=0; i--){
            for(int j = 0; j<5; j++){
                riga0.add(p.getMy_shelfie().getShelf()[i][j]);
            }
            if (checkRow(riga0)) count++;
            riga0.clear();
        }
        return count>4;
    }
    private boolean checkRow(Vector<Tiles> riga){
        int[] type = new int[5];
        for (int i = 0; i < riga.size()-1; i++) {
            for(int j = i+1; j< riga.size(); j++) {
                if (riga.get(i).getType() == riga.get(j).getType()) {
                    type[riga.get(i).getType()]++;

                    if (type[riga.get(i).getType()] > 3) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
