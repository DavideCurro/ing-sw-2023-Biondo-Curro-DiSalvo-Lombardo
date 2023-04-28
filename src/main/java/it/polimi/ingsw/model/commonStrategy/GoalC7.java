package it.polimi.ingsw.model.commonStrategy;

import java.io.Serializable;
import java.util.*;

import it.polimi.ingsw.model.player.Player;

import static it.polimi.ingsw.model.Playground.Tiles.NOT_VALID;


public class GoalC7 implements CommonObj , Serializable {
    /** Four lines each formed by 5 tiles of maximum three different types.
     *  One line can show the same or a different combination of another line.
     *  @param p
     *  @return
     *  */

    public boolean check(Player p) {
        Vector<Integer> v = new Vector<Integer>();//vector used to count how many types I have in one row
        int count = 0;
        boolean invalidRow = false;
        for(int i = 0; i<6; i++){
            for(int j =0; j<5; j++){
                if(v.size() > 3)//Already more than 3 types in one row, check the next one
                    break;
                else {
                    int type = p.getMy_shelfie().getShelf()[i][j].getType();
                    if (type == NOT_VALID) {
                        invalidRow = true;
                        break;
                    }
                    if (v.contains(type))
                        continue;
                    else
                        v.add(type);//new type read in the row
                }
            }
            if(v.size() <= 3 && !v.isEmpty() && !invalidRow)
                count++;//needs four rows with just max three types in it
            v.clear();
            if(count == 4)
                return true;
            invalidRow = false;
        }

        return false;
    }
}
