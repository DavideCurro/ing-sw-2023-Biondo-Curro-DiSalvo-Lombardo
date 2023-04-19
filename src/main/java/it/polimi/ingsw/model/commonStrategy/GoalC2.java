package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

/*Goal 2 = Two columns each formed by 6 different types of tiles.*/
public class GoalC2 implements CommonObj{
    private int count = 0;

    public boolean check(Player p) {
        for (int j = 0; j < 5; j++) {
            int i = 1;
            while (((p.getMy_shelfie().getShelf()[i][j]).getType() != (p.getMy_shelfie().getShelf()[ i - 1][j]).getType())) {
                i++;
                if (i == 5) {
                    count++;
                    break;
                }
            }
            if (count == 2) //count how many columns are fill with 6 equal rows
                return true;
        }
        return false;

    }

}
