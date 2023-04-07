package it.polimi.ingsw;

//import java.util.Arrays;
import java.util.Vector;

public class Library {
    private Tiles[][] shelf;


    public Library() {
        this.shelf = new Tiles[6][5];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                this.shelf[i][j] = new Tiles(-1, i, j);
            }
        }
    }

    public Library(Tiles[][] shelf) {
        this.shelf = shelf;
    }

    public Tiles[][] getShelf() {
        return shelf;
    }

    public void setShelf(Tiles[][] shelf) {
        this.shelf = shelf;
    }

    /**
     * This method places the tiles picked up by the player's shelfie onto their shelf in the specified column.
     *
     * @param t      The Vector of tiles picked up by the player's shelfie.
     * @param column The column in which the tiles should be placed.
     * @param len    The number of tiles to be placed.
     * @param p      The playground from which the tiles were picked up.
     * @return A boolean value indicating whether the tiles were successfully placed on the shelf.
     */
    public Boolean posix(Coordinate t, int column, int len, Playground p) {
// Initialize variables
        int[] count = this.available();
        int j = 0;
        Boolean tmp = p.adiacency(t);
        System.out.println(tmp);
        //System.out.println(t.size());
// Place the tiles on the shelf
        for (int i = count[column]; i < len + count[column]; i++) {
            if (tmp) {
// Set the type of the tile on the shelf and remove it from the playground
                this.shelf[5 - i][column].setType(t.getTypeByIndex(j));
               // System.out.println(this.shelf[5 - i][column].getType());
                p.getGround()[t.getXByIndex(j)][t.getYByIndex(j)].setType(-1);
                j++;
            } else {
// If the tiles cannot be placed, print an error message
                System.out.println("NOT POSSIBLE");
            }
        }
        return tmp;
    }

    public void printOut(Library s) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(s.shelf[i][j].getType() + "\t");
            }
            System.out.println();
        }
    }

    /**
     * This method calculates the number of free spots available in each column of the player's shelfie.
     *
     * @return An array of integers representing the number of free spots in each column of the shelfie.
     */
    public int[] available() {
// Initialize variables
        int[] count = new int[5];

// Check the number of free spots in each column of the player's shelfie
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 6; i++) {
                if ((this.shelf[i][j]).getType() == -1)
                    count[j] = 5 - i;
            }
        }

// Return the array of free spots in each column
        return count;
    }
}
