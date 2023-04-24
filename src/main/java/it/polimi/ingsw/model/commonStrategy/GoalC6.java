package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;
import java.sql.Struct;
import java.util.Vector;

//Goal 6 = Two lines each formed by 5 different types of tiles.
public class GoalC6 implements CommonObj, Serializable {
    private int count=1;
    private Vector<Integer> types = new Vector<>();
    public boolean check(Player p){
        int differentColumn=0;
        for(int i = 0; i < 6; i++ ){
            for (int j = 0; j<5; j++) {
                if (types.contains(p.getMy_shelfie().getShelf()[i][j].getType()))   break;
                else types.add(p.getMy_shelfie().getShelf()[i][j].getType());
            }
            if(types.size() == 5){
                differentColumn++;
            }
            types.clear();
        }
        if(differentColumn >1) return true;
        return false;
    }
}
