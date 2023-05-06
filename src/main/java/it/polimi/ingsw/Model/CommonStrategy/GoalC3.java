package it.polimi.ingsw.Model.CommonStrategy;

import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;

import java.io.Serializable;

public class GoalC3 implements CommonObj, Serializable {
    public GoalC3(){

    }

    /**
     * Four groups each containing at least
     * 4 tiles of the same type (not necessarily
     * in the depicted shape).
     * The tiles of one group can be different
     * from those of another group.
     * @param p
     * @return
     */
    public boolean check(Player p) {
        int column=0;
        boolean right = false;
        boolean down = false;
        for(int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (right(p.getMy_shelfie(), i, j)&& !right) {
                    column++;
                    right = true;
                }
                if(down(p.getMy_shelfie(),i,j)&& !down){
                    if(i>0)
                        if(down(p.getMy_shelfie(),i-1,j))
                            continue;
                    column++;
                    down = true;
                }
            }
            right = false;
            down = false;
        }
        return column >=4;
    }

    private boolean right(Library l,int i,int j){
        int count = 0;
        for(int k = j; k<5;k++){
            if(l.getShelf()[i][j].getType() == l.getShelf()[i][k].getType()){
                count++;
            }else{
                break;
            }
        }
        return count>=4;
    }

    private boolean down(Library l, int i, int j){
        int count = 0;
        for(int k = i; k <6;k++){
            if(l.getShelf()[i][j].getType() == l.getShelf()[k][j].getType()){
                count++;
            }else{
                break;
            }
        }
        return count>=4;
    }

}
