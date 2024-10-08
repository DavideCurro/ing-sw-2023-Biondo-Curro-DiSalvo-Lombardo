package it.polimi.ingsw.Model.Playground;


import it.polimi.ingsw.Model.Exception.PlaygroundException;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

import static it.polimi.ingsw.Model.Playground.Tiles.*;

/**
 * The class Playground
 */
public class Playground implements Serializable {
    private final static int[][] mask2Player = new int[][]{{-1, -1,  -1, -1, -1, -1, -1, -1, -1},
                                                           {-1, -1,  -1,  0,  0, -1, -1, -1, -1},
                                                           {-1, -1,  -1,  0,  0,  0, -1, -1, -1},
                                                           {-1, -1,   0,  0,  0,  0,  0,  0, -1},
                                                           {-1,  0,   0,  0,  0,  0,  0,  0, -1},
                                                           {-1,  0,   0,  0,  0,  0,  0, -1, -1},
                                                           {-1, -1,  -1,  0,  0,  0, -1, -1, -1},
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
        this.fillUP();
    }

    /**
     *
     * It is a constructor.
     *
     *
     *
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
                    g[i][j] = new Tiles(r.nextInt(6), i, j);
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
        int countAlone = 0;
        for(int i = 0;i<9;i++){
            for(int j = 0; j<9;j++){
                if(this.ground[i][j].getType()!= NOT_VALID){
                    if(amIAlone(i,j))
                        countAlone++;
                    count ++;
                }
            }
        }
        if((count == countAlone) || (count < 3)) {
            fillUP();
        }

    }
    public boolean amIAlone(int i, int j){
        try{
            if((!this.ground[i-1][j].checkSet()) && (!this.ground[i+1][j].checkSet()) &&(!this.ground[i][j-1].checkSet())&& (!this.ground[i][j+1].checkSet()))
                return true;
        }catch (IndexOutOfBoundsException e){
            return true;
        }
        return false;
    }



    /**
     *
     * Adjacency
     *
     * @param c  the coordinate of tiles.
     * @return boolean
     */
    public boolean adjacency(Vector<Tiles> c) {
        boolean done = false;
        for (Tiles tiles : c) {
            if (!isValid(tiles.getX(), tiles.getY())) return false;
            if(c.size() == 1)   return true;
        }
        for (int i = 0;i<c.size();i++) {
            if(!done)
                for(int j = i; j< c.size();j ++){
                    if(j == i)  continue;
                    done = c.get(j).checkSides(c, j, c.get(i).getX(), c.get(i).getY());
                }
            else{
                break;
            }
        }
        return done;

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
        try {
            if (this.ground[x - 1][y].getType() == NOT_VALID) return true;
            if (this.ground[x + 1][y].getType() == NOT_VALID) return true;
            if (this.ground[x][y - 1].getType() == NOT_VALID) return true;
            return this.ground[x][y + 1].getType() == NOT_VALID;
        }catch (IndexOutOfBoundsException e){
            return true;
        }
    }



}
