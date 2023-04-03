package it.polimi.ingsw.commonStrategy;

//Goal 11 = Five tiles of the same type forming a diagonal.


import it.polimi.ingsw.Player;

public class GoalC11 {
    private int count = 0;
    //check secondary diagonal (where i+5 = j)
    public void check(Player p) {
        for (int j = 0; j < 5; j++) {
            int i = 5;
            while (((p.getMy_shelfie().getShelf()[i][j]).getType() == (p.getMy_shelfie().getShelf()[i - 1][j]).getType())) {
                    i--;
                    count ++;
            }
            if(count == 4){
                System.out.println("The diagonal has been formed");
            }
        }

        //check secondary diagonal (where i = j+4)
        for (int i = 0; i < 6; i++) {
            int j = 4;
            while (((p.getMy_shelfie().getShelf()[i][j]).getType() == (p.getMy_shelfie().getShelf()[i+1][j-1]).getType())) {
                j--;
                count ++;
            }
            if(count == 4){
                System.out.println("The diagonal has been formed");
            }
        }

        //check primary diagonal (where i=j)
        for(int j = 0; j < 5; j++){
            for(int i = 0; i < 6; i++){
                while((p.getMy_shelfie().getShelf()[i][j]).getType() == (p.getMy_shelfie().getShelf()[i][j]).getType()){
                    count ++;
                }
                if(count == 4){
                    System.out.println("The diagonal has been formed");
                }
            }
        }
        //check primary diagonal (where i+1=j)
        for(int j = 0; j < 5; j++){
            for(int i = 1; i < 6; i++){
                while((p.getMy_shelfie().getShelf()[i][j]).getType() == (p.getMy_shelfie().getShelf()[i][j]).getType()){
                    count ++;
                }
                if(count == 4){
                    System.out.println("The diagonal has been formed");
                }
            }
        }
    }
}
