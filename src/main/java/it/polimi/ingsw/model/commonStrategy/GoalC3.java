package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;

public class GoalC3 implements CommonObj{
    public GoalC3(){

    }
    public boolean check(Player p) {
        int column=0;
        for(int i = 0; i < 6; i++){
            if(checkRight(p.getMy_shelfie(),i) % 4 == 0){
                column ++;
           }
            if(i != 5)
                if(checkDown(p.getMy_shelfie(),i) % 4 == 0){
                    column ++;
                }
        }
        if(column >= 4){
            return true;
        }
        return false;
    }
    private int checkRight(Library l, int i){
        int count = 1;
        int j=0;
        for(int k = 1;  k<5; k++){
            if(l.getShelf()[i][k].getType() == l.getShelf()[i][j].getType()){
                if(count < 4)
                    count ++;
                else return count;
            }
            else{
                if(k >= 2){
                    return 0;
                }
                j++;
                k = j;
                count = 1;

            }
        }
        return  count;
    }
    private int checkDown(Library l, int j){
        int count = 0;
        int i=0;
        for(int k = 1;  k<6; k++){
            if(l.getShelf()[i][j].getType() == l.getShelf()[k][j].getType()){
                if(count < 4)
                    count ++;
                else return count;
            }
            else{
                if(k >= 2){
                    return 0;
                }
                i++;
                k = i;
                count = 0;

            }
        }
        return  count;
    }

}
