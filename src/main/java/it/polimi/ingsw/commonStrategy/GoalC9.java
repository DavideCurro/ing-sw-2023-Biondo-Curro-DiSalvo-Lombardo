package it.polimi.ingsw.commonStrategy;

/*Goal 9 = Eight tiles of the same type. There’s no
restriction about the position of these
tiles  */

import it.polimi.ingsw.Player;

public class GoalC9 {
    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;
    private int count5 = 0;
    private int count6 = 0;


    public boolean check(Player p) {
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 6; i++) {
                int type = p.getMy_shelfie().getShelf()[i][j].getType();
                switch (type) {
                    case 1:
                        count1++;
                        if (count1 == 8) {
                            return true;
                        } else {
                            break;
                        }
                    case 2:
                        count2++;
                        if (count2 == 8) {
                            return true;
                        } else {
                            break;
                        }
                    case 3:
                        count3++;
                        if (count3 == 8) {
                            return true;
                        } else {
                            break;
                        }
                    case 4:
                        count4++;
                        if (count4 == 8) {
                            return true;
                        } else {
                            break;
                        }
                    case 5:
                        count5++;
                        if (count5 == 8) {
                            return true;
                        } else {
                            break;
                        }
                    case 6:
                        count6++;
                        if (count6 == 8) {
                            return true;
                        } else {
                            break;
                        }
                }
            }
        }
        return false;
    }
}