package it.polimi.ingsw.model.commonStrategy;

import java.io.Serializable;
import java.util.*;

import it.polimi.ingsw.model.player.Player;


public class GoalC5 implements CommonObj, Serializable {
    /** Three columns each formed by 6 tiles of maximum three different types.
     * One column can show the same or a different combination of another column.
     * @param p player to check
     * @return
     * */
    public boolean check(Player p) {
        Vector<Integer> v = new Vector<Integer>();//vector used to count how many types I have in one column
        int count = 0;

        for(int j =0; j<5; j++){
            for(int i = 0; i<6; i++){
                if(v.size() > 3)//Already more than 3 types in one column, check the next one
                    break;
                else {
                    int type = p.getMy_shelfie().getShelf()[i][j].getType();
                    if (!v.contains(type))
                        v.add(type);//new type read in the column
                }
            }
            if(v.size() <= 3)
                count++;//needs three columns with just max three types in it
            v.clear();
            if(count == 3)
                return true;
        }

        return false;
    }
}
