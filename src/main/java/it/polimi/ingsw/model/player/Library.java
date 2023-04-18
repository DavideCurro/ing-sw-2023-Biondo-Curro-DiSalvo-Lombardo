package it.polimi.ingsw.model.player;


import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.exception.LibraryException;
import static it.polimi.ingsw.model.Playground.Tiles.*;
import java.util.Random;
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
                this.shelf[i][j] = new Tiles(NOT_VALID, i, j);
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

     Method to place picked tiles on the player's shelf, given a column to place them in and their coordinates on the playground.

     @param t The coordinates of the picked tiles
     @param column The column on the player's shelf to place the tiles in
     @param len The number of tiles to place on the shelf
     @param p The playground object where the tiles were originally placed
     @return true if the tiles were successfully placed on the shelf, false otherwise
     */
    public Boolean posix(Vector<Tiles> t, int column, int len, Playground p, int count) throws LibraryException {
        // Initialize variables
        int j = 0;
        Boolean tmp = p.adjacency(t);
        //System.out.println(tmp);
        // Place the tiles on the shelf
        for (int i =5; i > len-1; i--) {
            if (tmp) {
                if(j == len) return true;
                if(t.get(j).checkSet()) {
                    if(this.shelf[i][column].getType() == NOT_VALID)
                        this.shelf[i][column].setType(t.get(j).getType()); //go to last element in column possible, 6 - i that's mean the last NOT_VALID tile
                    else
                        this.shelf[i-(count+1)][column].setType(t.get(j).getType());
                    p.getGround()[t.get(j).getX()][t.get(j).getY()].setType(NOT_VALID);// Set the type of the tile on the shelf and remove it from the playground
                    j++;
                }else throw new LibraryException("Already Picked");
            } else {
                // If the tiles cannot be placed, print an error message
                throw new LibraryException("Not adjacent");

            }
        }
        return tmp;
    }



    /**
     * This method calculates the number of free spots available in each column of the player's shelf.
     *
     * @return An array of integers representing the number of free spots in each column of the shelf.
     */
    public int available(int column) throws LibraryException { //Count the NOT_VALID tiles, and reverse it to find the last significant tile
// Initialize variables
        int count = 0;

// Check the number of free spots in each column of the player's shelf
        for (int i = 0; i < 6; i++) {
            if ((this.shelf[i][column]).getType() == NOT_VALID)
                count ++;
             //   System.out.println(count);
        }
        if((6-count) == 6) throw new LibraryException("Impossible to use this column");

// Return the array of free spots in each column
        return 6-count;
    }
    public boolean isFull(){
        for(int j = 0; j < 5; j++){
            if(this.shelf[0][j].getType() == NOT_VALID)
                return false;
        }
        return true;
    }
}
