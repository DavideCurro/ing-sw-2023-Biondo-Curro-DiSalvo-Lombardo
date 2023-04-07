package it.polimi.ingsw.commonStrategy;

import java.util.*;
import it.polimi.ingsw.Player;

public class GoalC5 {

    public boolean check(Player p) {
        Vector<Integer> v = new Vector<Integer>();//vector used to count how many types I have in one column
        int count = 0;

        for(int j =0; j<5; j++){
            for(int i = 0; i<6; i++){
                int type = p.getMy_shelfie().getShelf()[i][j].getType();
                if(v.size() > 3)//Already more than 3 types in one column, check the next one
                    break;
                else {
                    if (v.contains(type))
                        continue;
                    else
                        v.add(type);//new type read in the column
                }
                if((v.size() <= 3) && (i == 5))
                    count++;//needs three columns with just max three types in it
            }
            v.clear();
            if(count == 3)
                return true;
        }

        return false;
    }
}
