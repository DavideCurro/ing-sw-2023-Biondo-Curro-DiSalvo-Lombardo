package it.polimi.ingsw.model;


/**
 * The class Tiles
 */
public class Tiles {

    private int type;
    private int x;
    private int y;



    /**
     *
     * It is a constructor.
     *
     */
    public Tiles(){

        type=-1;
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
    public int[] getXY(){

        int[] coordinate = new int[2];
        coordinate[0] = this.getX();
        coordinate[1] = this.getY();
        return coordinate;
    }

    @Override

/**
 *
 * To string
 *
 * @return String
 */
    public String toString() {

        return "Tiles{" +
                "type=" + type +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
