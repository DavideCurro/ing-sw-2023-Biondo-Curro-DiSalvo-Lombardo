package it.polimi.ingsw.commonStrategy;

import java.util.*;
import it.polimi.ingsw.Player;

/*Four lines each formed by 5 tiles of maximum three different types.
One line can show the same or a different combination of another line. */

public class GoalC7 {
    public boolean check(Player p) {
        Vector<Integer> v = new Vector<Integer>();//vector used to count how many types I have in one row
        int count = 0;

        for(int i = 0; i<6; i++){
            for(int j =0; j<5; j++){
                if(v.size() > 3)//Already more than 3 types in one row, check the next one
                    break;
                else {
                    int type = p.getMy_shelfie().getShelf()[i][j].getType();
                    if (v.contains(type))
                        continue;
                    else
                        v.add(type);//new type read in the row
                }
            }
            if(v.size() <= 3)
                count++;//needs four rows with just max three types in it
            v.clear();
            if(count == 4)
                return true;
        }

        return false;
    }
}
