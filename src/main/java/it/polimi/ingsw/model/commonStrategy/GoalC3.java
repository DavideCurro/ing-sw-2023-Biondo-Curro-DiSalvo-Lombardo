package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;

public class GoalC3 implements CommonObj{
    public GoalC3(){

    }
    public boolean check(Player p) {
        int column=0;   //todo: simplify, try to merge
        for(int i = 0; i < 6; i++){
            if(checkRight(p.getMy_shelfie(),i) % 4 == 0){
                column ++;
           }
            if(i != 5)
                if(checkDown(p.getMy_shelfie(),i) % 4 == 0){
                    column ++;
                }
        }
        if(column == 4){
            return true;
        }
        /*
        for(int j= 0, i = 0; (j<5 && i<6); i++,j++){
            int[] count = checkRightAndDown(p.getMy_shelfie(),i,j);
        }*/
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
/* todo: check if is correct
    private int[] checkRightAndDown(Library l, int i, int j) {
        int[] count = new int[2];
        int k = 0;
        int y = 1;
        int r = 1;
        while (k < 5 && y < 6 && r <5) {
            if (l.getShelf()[i][k].getType() == l.getShelf()[i][r].getType()) {
                if (count[0] < 4)
                    count[0]++;
                else
                    return count;
            } else {
                count[0]= 1;
            }
            if (l.getShelf()[k][j].getType() == l.getShelf()[y][j].getType()) {
                if (count[1] < 4)
                    count[1]++;
                else
                    return count;
            } else {
                count[1] = 1;
            }
            k++;
            r++;
            y++;
        }
        return count;
    }
    */
}
