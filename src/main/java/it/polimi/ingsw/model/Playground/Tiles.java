package it.polimi.ingsw.model.Playground;


import java.util.Vector;

/**
 * The class Tiles
 */
public class Tiles {
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
     */
    public Tiles(){

        type=NOT_VALID;
        x=0;
        y=0;
    }

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
     * Sets the X
     *
     * @param x  the x.
     */
    public void setX(int x) {

        this.x = x;
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
     * Sets the Y
     *
     * @param y  the y.
     */
    public void setY(int y) {

        this.y = y;
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


    /**

     Adds a new coordinate to the collection.
     @param x the x-coordinate of the new coordinate
     @param y the y-coordinate of the new coordinate
     @param type the type of the new coordinate
     */
    public void setALL(int x,int y,int type){
        this.setX(x);
        this.setY(y);
        this.setType(type);
    }
    public boolean checkSet(){
        return this.type != NOT_VALID;
    }

    public boolean checkSides(Vector<Tiles> c, int i){
        return (checkLeft(c,i) || checkRight(c,i) || checkUp(c,i) || checkDown(c,i));
    }

    /**
     *
     * Check left
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on left side for the index one
     */
    private boolean checkUp(Vector<Tiles> c, int i ) {
        return c.get(i - 1).getX()-1 == c.get(i).getX() && c.get(i-1).getY() == c.get(i).getY();
    }

    /**
     *
     * Check right
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on right side for the index one
     */
    private boolean checkDown(Vector<Tiles> c, int i ){
        return c.get(i - 1).getX()+1 == c.get(i).getX()  && c.get(i-1).getY() == c.get(i).getY();
    }

    /**
     *
     * Check up
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on upper side for the index one
     */
    private boolean checkLeft(Vector<Tiles> c, int i ){
        return c.get(i - 1).getY() -1 == c.get(i).getY() && c.get(i-1).getX() == c.get(i).getX();
    }

    /**
     *
     * Check down
     *
     * @param c  the coordinate.
     * @param i  the index.
     * @return boolean, return true if the two tiles are next to each other on downside for the index one
     */
    private boolean checkRight(Vector<Tiles> c, int i ){
        return c.get(i - 1).getY() +1 == c.get(i).getY()&& c.get(i-1).getX() == c.get(i).getX();
    }


}
