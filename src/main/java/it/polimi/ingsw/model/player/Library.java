package it.polimi.ingsw.model.player;


import it.polimi.ingsw.model.Coordinate;
import it.polimi.ingsw.model.Playground;
import it.polimi.ingsw.model.Tiles;
import it.polimi.ingsw.model.exception.CoordinateStateException;
/**
 * The class Library
 */
public class Library {
    private Tiles[][] shelf;

    /**
     *
     * It is a constructor.
     *
     */

    public Library() {
        this.shelf = new Tiles[6][5];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                this.shelf[i][j] = new Tiles(-1, i, j);
            }
        }
    }

    /**
     *
     * It is a constructor.
     *
     * @param shelf  the shelf.
     */
    public Library(Tiles[][] shelf) {
        this.shelf = shelf;
    }

    /**
     *
     * Gets the shelf
     *
     * @return the shelf
     */
    public Tiles[][] getShelf() {
        return shelf;
    }

    /**
     *
     * Sets the shelf
     *
     * @param shelf  the shelf.
     */
    public void setShelf(Tiles[][] shelf) {
        this.shelf = shelf;
    }

    /**

     Method to place picked tiles on the player's shelf, given a column to place them in and their coordinates on the playground.

     @param t The coordinates of the picked tiles
     @param column The column on the player's shelf to place the tiles in
     @param len The number of tiles to place on the shelf
     @param p The playground object where the tiles were originally placed
     @return true if the tiles were successfully placed on the shelf, false otherwise
     */
    public Boolean posix(Coordinate t, int column, int len, Playground p) {
        // Initialize variables
        int[] count = this.available();
        int j = 0;
        Boolean tmp = p.adiacency(t);
        System.out.println(tmp);
        // Place the tiles on the shelf
        for (int i = count[column]; i < len + count[column]; i++) {
            if (tmp) {
                // Set the type of the tile on the shelf and remove it from the playground
                try {
                    this.shelf[5 - i][column].setType(t.getTypeByIndex(j));
                    // System.out.println(this.shelf[5 - i][column].getType());
                    p.getGround()[t.getXByIndex(j)][t.getYByIndex(j)].setType(-1);
                } catch (CoordinateStateException e) {
                    System.out.println(e.getMessage() + "SOME ERROR ON COORDINATE OBJECT OCCURRED");
                }

                j++;
            } else {
                // If the tiles cannot be placed, print an error message
                System.out.println("NOT POSSIBLE");
            }
        }
        return tmp;
    }

    /**
     *
     * Print output
     *
     *
     */
    public void printOut() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(this.shelf[i][j].getType() + "\t");
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
