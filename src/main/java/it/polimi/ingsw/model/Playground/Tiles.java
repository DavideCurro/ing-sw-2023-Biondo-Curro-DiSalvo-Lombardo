package it.polimi.ingsw.model.Playground;


import java.io.Serializable;
import java.util.Vector;

/**
 * The class Tiles
 */
public class Tiles implements Serializable {
    public static final int NOT_VALID = -1;
    public static final int BLUE = 0;
    public static final int CYAN = 1;
    public static final int YELLOW = 2;
    public static final int WHITE = 3;
    public static final int GREEN = 4;
    public static final int PINK = 5;

    private int type;
    private int x;
    private int y;


    /**
     *
     * It is a constructor.
     *
     * @param type  the type.
     */
    public Tiles(int type){

        this.type = type;
        this.x = 0;
        this.y = 0;
    }

    /**
     *
     * It is a constructor.
     *
     * @param type  the type.
     * @param x  the x.
     * @param y  the y.
     */
    public Tiles(int type,int x,int y){

        this.type = type;
        this.x = x;
        this.y = y;
    }


    /**
     *
     * Gets the type
     *
     * @return the type
     */
    public int getType() {

        return type;
    }


    /**
     *
     * Sets the type
     *
     * @param type  the type.
     */
    public void setType(int type) {

        this.type = type;
    }


    /**
     *
     * Gets the X
     *
     * @return the  X
     */
    public int getX() {

        return x;
    }


    /**
     *
     * Gets the Y
     *
     * @return the  Y
     */
    public int getY() {

        return y;
    }



    /**
     *
     * Gets the XY
     *
     * @return the  XY
     */
    public int[] getXYType(){

        int[] coordinate = new int[3];
        coordinate[0] = this.x;
        coordinate[1] = this.y;
        coordinate[2] = this.type;
        return coordinate;
    }

    @Override

    public String toString() {

        return "Tiles{" +
                "type=" + type +
                ", x=" + x +
                ", y=" + y +
                '}';
    }


    public boolean checkSet(){
        return this.type != NOT_VALID;
    }

    public boolean checkSides(Vector<Tiles> c, int i, int x, int y){
        return (checkLeft(c,i,x,y) || checkRight(c,i,x,y) || checkUp(c,i,x,y) || checkDown(c,i,x,y));
    }

    /**
     *
     * Check up
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on left side for the index one
     */
    private boolean checkUp(Vector<Tiles> c, int i , int x, int y) {
        return c.get(i).getX() == x-1 && c.get(i).getY() == y;
    }

    /**
     *
     * Check down
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on right side for the index one
     */
    private boolean checkDown(Vector<Tiles> c, int i, int x, int y ){
        return c.get(i ).getX() == x+1  && c.get(i).getY() == y;
    }

    /**
     *
     * Check left
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on upper side for the index one
     */
    private boolean checkLeft(Vector<Tiles> c, int i , int x, int y ){
        return c.get(i).getY()  == y-1 && c.get(i).getX() == x;
    }

    /**
     *
     * Check right
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on downside for the index one
     */
    private boolean checkRight(Vector<Tiles> c, int i , int x, int y ){
        return c.get(i).getY()  == y+1 && c.get(i).getX() == x;
    }


}
