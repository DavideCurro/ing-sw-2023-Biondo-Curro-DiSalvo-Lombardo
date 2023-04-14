package it.polimi.ingsw.model.Playground;


import it.polimi.ingsw.model.exception.PlaygroundException;

import java.util.Random;
import java.lang.*;
import java.util.Vector;

import static it.polimi.ingsw.model.Playground.Tiles.*;

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
     * It is a constructor. It uses num_players for setup mask
     *
     * @param num_players  the num_players.
     * @throws PlaygroundException throws an exception for unexpected value of num_player, is a check for 2 < num_player < 4
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
                if (maskPlayer[i][j] != NOT_VALID) {
                    g[i][j] = new Tiles(r.nextInt(5), i, j);
                } else {
                    g[i][j] = new Tiles(NOT_VALID, i, j);
                }
            }
        }
        setGround(g);
    }


    public int getNum_players() {
        return num_players;
    }

    public void setNum_players(int num_players) {
        this.num_players = num_players;
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
     * FillUp the matrix when is empty
     *
     * @throws   PlaygroundException throws an exception for unexpected value of num_player, is a check for 2 < num_player < 4
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
        int count = 0;
        for(int i = 0;i<9;i++){
            for(int j = 0; j<9;j++){
                if(this.ground[i][j].getType()!= NOT_VALID){
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
     * Adjacency
     *
     * @param c  the coordinate of tiles.
     * @return boolean
     */
    public boolean adjacency(Vector<Tiles> c) {
        int startX = c.get(0).getX();
        int startY = c.get(0).getY();
        if(isValid(startX,startY)){
            for (int i = 1; i < c.size(); i++) {
                if(isValid(c.get(i).getX(),c.get(i).getY())){
                    if(c.get(i).checkSides(c,i)){
                        return  false;
                    }
                }else{
                    return false;
                }
            }
        }else{
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
    //todo: investigate why (y-1) > -1 is always true (I think is an error of Intellij)
    private boolean isValid(int x, int y){
        if(x-1 > -1 && this.ground[x-1][y].getType() == NOT_VALID)                          return true;
        else if (this.ground[x+1][y].getType() == NOT_VALID && x+1 < this.ground.length)    return true;
        else if (this.ground[x][y-1].getType() == NOT_VALID && y-1 > -1)                    return true;
        else if (this.ground[x][y+1].getType() == NOT_VALID && y+1 < this.ground[0].length) return true;
        else    return x + 1 > this.ground.length || y + 1 > this.ground[0].length;
    }



}
