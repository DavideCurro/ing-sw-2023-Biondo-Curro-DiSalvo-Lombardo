package it.polimi.ingsw.model.commonStrategy;

//Goal 11 = Five tiles of the same type forming a diagonal.


import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Player;

import java.util.Vector;

public class GoalC11 implements CommonObj{
    private int count = 0;
    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;

    public boolean check(Player p) {

        Vector<Tiles> tmp = new Vector<>();
        Vector<Tiles> tmp1 = new Vector<>();
        Vector<Tiles> tmp2 = new Vector<>();
        Vector<Tiles> tmp3 = new Vector<>();


        tmp.add(new Tiles(p.getMy_shelfie().getShelf()[1][4].getType(), 1, 4));
        tmp.add(new Tiles(p.getMy_shelfie().getShelf()[2][3].getType(), 2, 3));
        tmp.add(new Tiles(p.getMy_shelfie().getShelf()[3][2].getType(), 3, 2));
        tmp.add(new Tiles(p.getMy_shelfie().getShelf()[4][1].getType(), 4, 1));
        tmp.add(new Tiles(p.getMy_shelfie().getShelf()[5][0].getType(), 5, 0));

        int type = tmp.get(1).getType();

        for (Tiles tiles : tmp) {
            if (p.getMy_shelfie().getShelf()[tiles.getX()][tiles.getY()].getType() != type) {
                return false;
            }
            return true;
        }

        tmp1.add(new Tiles(p.getMy_shelfie().getShelf()[1][0].getType(), 1, 0));
        tmp1.add(new Tiles(p.getMy_shelfie().getShelf()[2][1].getType(), 2, 1));
        tmp1.add(new Tiles(p.getMy_shelfie().getShelf()[3][2].getType(), 3, 2));
        tmp1.add(new Tiles(p.getMy_shelfie().getShelf()[4][3].getType(), 4, 3));
        tmp1.add(new Tiles(p.getMy_shelfie().getShelf()[5][4].getType(), 5, 4));

        int type1 = tmp.get(1).getType();

        for (Tiles tiles : tmp) {
            if (tiles.getType() != type1) {
                return false;
            }
            return true;
        }

      /*  //check secondary diagonal (where i+5 = j) gialla (sbagliata)
        for (int i = 1; i < 5; i++) {
            for(int j = 4; j < 5; j--){
                if(((p.getMy_shelfie().getShelf()[i][j]).getType() == (p.getMy_shelfie().getShelf()[i+1][j-1]).getType())) {
                //j--;
                count++;
            }
            }
            if(count == 4){
                System.out.println("The diagonal has been formed");
                return true;
            }
        }*/


        //check secondary diagonal (where i = j+4) (giusta verde)
        for (int i = 0; i < 6; i++) {
            int j = 4;
            while (((p.getMy_shelfie().getShelf()[i][j]).getType() == (p.getMy_shelfie().getShelf()[i+1][j-1]).getType())) {
                j--;
                count1 ++;
            }
            if(count1 == 4){
                System.out.println("The diagonal has been formed");
                return true;
            }
        }

        //check primary diagonal (where i=j) (giusta blu)
        for(int j = 0; j < 5; j++){
            for(int i = 0; i < 6; i++){
                while((p.getMy_shelfie().getShelf()[i][j]).getType() == (p.getMy_shelfie().getShelf()[i][j]).getType()){
                    count2 ++;
                }
                if(count2 == 4){
                    System.out.println("The diagonal has been formed");
                    return true;
                }
            }
        }


       /* //check primary diagonal (where i+1=j) (sbagliata rossa)
        for(int j = 0; j < 5; j++){
            for(int i = 1; i < 6; i++){
                while((p.getMy_shelfie().getShelf()[i][j]).getType() == (p.getMy_shelfie().getShelf()[i][j]).getType()){
                    count3 ++;
                }
                if(count3 == 4){
                    System.out.println("The diagonal has been formed");
                    return true;
                }
            }
        }*/
        return false;
    }
}