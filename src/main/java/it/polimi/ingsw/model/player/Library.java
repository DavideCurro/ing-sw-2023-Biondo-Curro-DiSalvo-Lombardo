package it.polimi.ingsw.model.player;


import it.polimi.ingsw.model.Playground;
import it.polimi.ingsw.model.Tiles;
import it.polimi.ingsw.model.exception.LibraryException;

import java.util.Vector;

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
    public Boolean posix(Vector<Tiles> t, int column, int len, Playground p) {
        // Initialize variables
        int count;
        try {
            count = this.available(column);
        }catch (LibraryException e){
            System.out.println("Impossible column, max number reached");
            return false;
        }
        int j = 0;
        Boolean tmp = p.adiacency(t);
        System.out.println(tmp);
        // Place the tiles on the shelf
        for (int i = count; i < len + count; i++) {
            if (tmp) {
                // Set the type of the tile on the shelf and remove it from the playground
                this.shelf[5 - i][column].setType(t.get(j).getType());
                // System.out.println(this.shelf[5 - i][column].getType());
                p.getGround()[t.get(j).getX()][t.get(j).getY()].setType(-1);

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
     * Print output*
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
    public int available(int column) throws LibraryException {
// Initialize variables
        int count = 0;

// Check the number of free spots in each column of the player's shelfie
        for (int i = 0; i < 6; i++) {
            if ((this.shelf[i][column]).getType() == -1)
                count ++;
             //   System.out.println(count);
        }
        if((6-count) == 6) throw new LibraryException("Impossible to use this column");

// Return the array of free spots in each column
        return 6-count;
    }
}
