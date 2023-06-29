package it.polimi.ingsw.Model.CommonStrategy;

import java.io.Serializable;
import java.util.*;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Tiles;

import static it.polimi.ingsw.Model.Playground.Tiles.NOT_VALID;


public class GoalC5 implements CommonObj, Serializable {
    private final int type = 5;
    public int getType(){
        return type;
    }
    /** Three columns each formed by 6 tiles of maximum three different types.
     * One column can show the same or a different combination of another column.
     * @param p player to check
     * @return
     * */
    public boolean check(Player p) {
        int count = 0;
        Vector<Tiles> riga0 = new Vector<>();
        for(int j = 0; j<5; j++) {
            for (int i = 5; i >=0; i--) {
                riga0.add(p.getMy_shelfie().getShelf()[i][j]);
            }
            if (checkColumn(riga0)) count++;
            riga0.clear();
        }
        return count>2;
    }
    private boolean checkColumn(Vector<Tiles> riga){
        int[] type = new int[5];
        for (int i = 0; i < riga.size() - 1; i++) {
            if (riga.get(i).getType() == riga.get(i+1).getType()) {
                type[riga.get(i).getType()] ++;
                i++;
                if(type[riga.get(i).getType()]>2){
                    return false;
                }
            }
        }
        return true;
    }
}
