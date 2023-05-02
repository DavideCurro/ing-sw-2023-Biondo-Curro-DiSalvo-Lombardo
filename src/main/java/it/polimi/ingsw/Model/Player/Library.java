package it.polimi.ingsw.Model.Player;


import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.Exception.LibraryException;
import static it.polimi.ingsw.Model.Playground.Tiles.*;

import java.io.Serializable;
import java.util.Vector;



/**
 * The class Library
 */
public class Library implements Serializable {
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
     @param p The playground object where the tiles were originally placed
     @return true if the tiles were successfully placed on the shelf, false otherwise
     */
    public Boolean posix(Vector<Tiles> t, int column, Playground p){
        // Initialize variables
        int j = 0;
        boolean tmp = p.adjacency(t);
        //System.out.println(tmp);
        // Place the tiles on the shelf
        if(!tmp) return false;
        for (Tiles tile : t) {
            if(tile.checkSet()) {
                this.shelf[lastFree(column)][column].setType(tile.getType()); //go to last element in column possible, 6 - i that's mean the last NOT_VALID tile
                p.getGround()[tile.getX()][tile.getY()].setType(NOT_VALID);// Set the type of the tile on the shelf and remove it from the playground
            }else return false;
        }
        return true;
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
    private int lastFree(int column){
        for(int i = 5; i>=0; i--){
            if(this.shelf[i][column].getType() == NOT_VALID){
                return i;
            }
        }
        return -1;
    }
    public boolean isEmpty(){
        for(int j = 0; j<5; j++){
            if(this.shelf[5][j].getType() != NOT_VALID) return false;
        }
        return true;
    }
}
