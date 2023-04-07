package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.CoordinateStateException;
import it.polimi.ingsw.model.exception.PlaygroundException;

import java.util.Random;
import java.lang.*;

/**
 * The class Playground
 */
public class Playground {
    private final static int[][] mask2Player = new int[][]{{-1, -1,  -1, -1, -1, -1, -1, -1, -1},
                                                           {-1, -1,  -1,  0,  0, -1, -1, -1, -1},
                                                           {-1, -1,  -1,  0,  0,  0, -1, -1, -1},
                                                           {-1, -1,   0,  0,  0,  0,  0,  0, -1},
                                                           {-1,  0,   0,  0,  0,  0,  0,  0, -1},
                                                           {-1,  0,   0,  0,  0,  0,  0, -1, -1},
                                                           {-1, -1,   0,  0,  0, -1, -1, -1, -1},
                                                           {-1, -1,  -1, -1,  0,  0, -1, -1, -1},
                                                           {-1, -1,  -1, -1, -1, -1, -1, -1, -1}
    };
    private final static int[][] mask3Player = new int[][]{{-1, -1, -1,  0, -1, -1, -1, -1, -1},
                                                           {-1, -1, -1,  0,  0, -1, -1, -1, -1},
                                                           {-1, -1,  0,  0,  0,  0,  0, -1, -1},
                                                           {-1, -1,  0,  0,  0,  0,  0,  0,  0},
                                                           {-1,  0,  0,  0,  0,  0,  0,  0, -1},
                                                           { 0,  0,  0,  0,  0,  0,  0, -1, -1},
                                                           {-1, -1,  0,  0,  0,  0,  0, -1, -1},
                                                           {-1, -1, -1, -1,  0,  0, -1, -1, -1},
                                                           {-1, -1, -1, -1, -1,  0, -1, -1, -1}
    };
    private final static int[][] mask4Player = new int[][]{{-1, -1, -1,  0, 0, -1, -1, -1, -1},
                                                           {-1, -1, -1,  0, 0,  0, -1, -1, -1},
                                                           {-1, -1,  0,  0, 0,  0,  0, -1, -1},
                                                           {-1,  0,  0,  0, 0,  0,  0,  0,  0},
                                                           { 0,  0,  0,  0, 0,  0,  0,  0,  0},
                                                           { 0,  0,  0,  0, 0,  0,  0,  0, -1},
                                                           {-1, -1,  0,  0, 0,  0,  0, -1, -1},
                                                           {-1, -1, -1,  0, 0,  0, -1, -1, -1},
                                                           {-1, -1, -1, -1, 0,  0, -1, -1, -1}
    };

    private int num_players;
    private Tiles[][] ground;


    /**
     *
     * It is a constructor.
     *
     */
    public Playground() {
        ground = new Tiles[0][0];
    }


    /**
     *
     * It is a constructor. It use num_players for setup mask
     *
     * @param num_players  the num_players.
     * @throws   PlaygroundException
     */
    public Playground(int num_players) throws PlaygroundException {
        this.num_players = num_players;
        switch (num_players) {
            case 2 -> maskSetup(mask2Player); 
            case 3 -> maskSetup(mask3Player);
            case 4 -> maskSetup(mask4Player);
            
            default -> throw new PlaygroundException("Unexpected value: " + num_players);
        }
    }

    /**
     *
     * It is a constructor.
     *
     * @param ground  the ground.
     * @param num_players  the num_players.
     */
    public Playground(Tiles[][] ground, int num_players) {
        this.num_players = num_players;
        this.ground = ground;
    }

    /**
     *
     * Mask setup
     *
     * @param maskPlayer  the mask player.
     */
    private void maskSetup(int[][] maskPlayer) {
        Tiles[][] g = new Tiles[9][9];
        Random r = new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (maskPlayer[i][j] != -1) {
                    g[i][j] = new Tiles(r.nextInt(5), i, j);
                } else {
                    g[i][j] = new Tiles(-1, i, j);
                }
            }
        }
        setGround(g);
    }

    /**
     *
     * Gets the ground
     *
     * @return the ground
     */
    public Tiles[][] getGround() {
        return ground;
    }

    /**
     *
     * Sets the ground
     *
     * @param ground  the ground.
     */
    public void setGround(Tiles[][] ground) {
        this.ground = ground;
    }

    /**
     *
     * Print output playground
     *
     */
    public void printOutPlayground() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(this.ground[i][j].getType() + "\t");
            }
            System.out.println();
        }
    }

    /**
     *
     * FillUp the matrix when is empty
     *
     * @throws   PlaygroundException
     */
    private void fillUP() throws PlaygroundException {
        switch (num_players) {
            case 2 -> maskSetup(mask2Player);
            case 3 -> maskSetup(mask3Player);
            case 4 -> maskSetup(mask4Player);
            default -> throw new PlaygroundException("Unexpected value: " + num_players);
        }
    }

    public void countSelected() throws PlaygroundException {
        Coordinate tmp =new Coordinate();
        int count = 0;
        for(int i = 0;i<9;i++){
            for(int j = 0; j<9;j++){
                if(this.ground[i][j].getType()!= -1){
                    count++;
                }
            }
        }
        if(count < 5) {
            fillUP();
        }

    }




    /**
     *
     * Adiacency
     *
     * @param c  the coordinate of tiles.
     * @return boolean
     */
    public boolean adiacency(Coordinate c) {
        try {
            int startX = c.getX().get(0);
            int startY = c.getYByIndex(0);
            if(isValid(startX,startY)){
                for (int i = 1; i < c.size(); i++) {
                    if(isValid(c.getXByIndex(i),c.getYByIndex(i))){
                        if(!(checkLeft(c,i) || checkRight(c,i) || checkUp(c,i) || checkDown(c,i))){
                            return  false;
                        }
                    }else{
                        return false;
                    }

                }
            }else{
                return false;
            }
        } catch (CoordinateStateException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     *
     * isValid
     *
     * @param x  the x.
     * @param y  the y.
     * @return boolean, is true if the tile has at least one free spot adjacent it
     */
    private boolean isValid(int x, int y){
        if(x-1>0 && this.ground[x-1][y].getType() == -1){
            return  true;
        } else if (this.ground[x+1][y].getType() == -1 && x+1 < this.ground.length) {
            return true;
        } else if (this.ground[x][y-1].getType() == -1 && y-1 >0){
            return true;
        } else if (this.ground[x][y+1].getType() == -1 && y+1 < this.ground[0].length) {
            return true;
        } else return (x - 1) < 0 || x + 1 > this.ground.length || (y-1) < 0 || y + 1 > this.ground[0].length;
    }

    /**
     *
     * Check left
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on left side for the index one
     */
    private boolean checkLeft(Coordinate c, int i ){
        try {
            return c.getXByIndex(i - 1) == c.getXByIndex(i) - 1;
        }catch (CoordinateStateException e){
            System.out.println("Tiles not Valid");
            return false;
        }
    }

    /**
     *
     * Check right
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on right side for the index one
     */
    private boolean checkRight(Coordinate c, int i ){
        try {
            return c.getXByIndex(i - 1) == c.getXByIndex(i) + 1;
        }catch (CoordinateStateException e){
            System.out.println("Tiles not Valid");
            return false;
        }
    }

    /**
     *
     * Check up
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on upper side for the index one
     */
    private boolean checkUp(Coordinate c, int i ){
        try {
            return c.getYByIndex(i - 1) == c.getYByIndex(i) - 1;
        }catch (CoordinateStateException e){
            System.out.println("Tiles not Valid");
            return false;
        }
    }

    /**
     *
     * Check down
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on down side for the index one
     */
    private boolean checkDown(Coordinate c, int i ){
        try {
            return c.getYByIndex(i - 1) == c.getYByIndex(i) + 1;
        }catch (CoordinateStateException e){
            System.out.println("Tiles not Valid");
            return false;
        }
    }
}
