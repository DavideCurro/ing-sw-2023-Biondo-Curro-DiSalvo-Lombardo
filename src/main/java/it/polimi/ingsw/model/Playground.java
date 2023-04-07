package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.CoordinateStateException;
import it.polimi.ingsw.model.exception.PlaygroundException;

import java.util.Random;
import java.lang.*;

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

    public Playground() {
        ground = new Tiles[0][0];
    }

    public Playground(int num_tiles, int num_players) throws PlaygroundException {
        this.num_players = num_players;
        switch (num_players) {
            case 2 -> maskSetup(mask2Player); 
            case 3 -> maskSetup(mask3Player);
            case 4 -> maskSetup(mask4Player);
            
            default -> throw new PlaygroundException("Unexpected value: " + num_players);
        }
    }

    public Playground(int num_tiles, Tiles[][] ground, int num_players) {
        this.num_players = num_players;
        this.ground = ground;
        int row = this.ground.length;
        int col = this.ground[0].length;
        if ((row * col) != num_tiles) {
            System.out.println("ERROR");
            System.exit(-1);
        }
    }

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

    public Tiles[][] getGround() {
        return ground;
    }

    public void setGround(Tiles[][] ground) {
        this.ground = ground;
    }

    public void printOutPlayground() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(this.ground[i][j].getType() + "\t");
            }
            System.out.println();
        }
    }

    public void fillUP() throws PlaygroundException {
        switch (num_players) {
            case 2 -> maskSetup(mask2Player);
            case 3 -> maskSetup(mask3Player);
            case 4 -> maskSetup(mask4Player);
            default -> throw new PlaygroundException("Unexpected value: " + num_players);
        }
    }

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
    private boolean checkLeft(Coordinate c, int i ){
        try {
            return c.getXByIndex(i - 1) == c.getXByIndex(i) - 1;
        }catch (CoordinateStateException e){
            System.out.println("Tiles not Valid");
            return false;
        }
    }
    private boolean checkRight(Coordinate c, int i ){
        try {
            return c.getXByIndex(i - 1) == c.getXByIndex(i) + 1;
        }catch (CoordinateStateException e){
            System.out.println("Tiles not Valid");
            return false;
        }
    }
    private boolean checkUp(Coordinate c, int i ){
        try {
            return c.getYByIndex(i - 1) == c.getYByIndex(i) - 1;
        }catch (CoordinateStateException e){
            System.out.println("Tiles not Valid");
            return false;
        }
    }
    private boolean checkDown(Coordinate c, int i ){
        try {
            return c.getYByIndex(i - 1) == c.getYByIndex(i) + 1;
        }catch (CoordinateStateException e){
            System.out.println("Tiles not Valid");
            return false;
        }
    }
}
