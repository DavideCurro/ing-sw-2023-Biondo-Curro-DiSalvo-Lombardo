package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.CoordinateStateException;

import java.util.Vector;
/**

 A class representing a collection of coordinates on a 2D plane.

 */
public class Coordinate {
    Vector<Integer> X;
    Vector<Integer> Y;
    Vector<Integer> type;
    /**

     Constructs a new empty Coordinate object.
     */

    public Coordinate() {
        this.X = new Vector<>();
        this.Y = new Vector<>();
        this.type = new Vector<>();
    }
    /**

     Constructs a Coordinate
     @param x the x-coordinate of the new coordinate
     @param y the y-coordinate of the new coordinate
     @param type the type of the new coordinate
     */
    public Coordinate(Vector<Integer> x, Vector<Integer> y, Vector<Integer> type) {
        this.X = x;
        this.Y = y;
        this.type = type;
    }

    /**
     *
     * Check consistence, for same size in all vector
     *
     * @return boolean
     */
    private boolean checkConsistence(){

        return (this.type.size() == this.Y.size()) &&(this.Y.size() == this.X.size());
    }

    /**
     *
     * Gets the type
     *
     * @return the type
     */
    public Vector<Integer> getType() {
        if(checkConsistence())
            return type;
        else throw new IllegalStateException("MISMATCH VECTOR: " + this.type.size());
    }

    /**

     Gets the type of the coordinate at the specified index.
     @param i the index of the coordinate
     @return the type of the coordinate at the specified index
     @throws CoordinateStateException if the index is out of bounds
     */
    public int getTypeByIndex(int i) throws CoordinateStateException {
        if(checkConsistence())
            if(i<=type.size())
                return type.get(i);
            else throw new CoordinateStateException("OutboundIndex");
        else throw new CoordinateStateException("MISMATCH VECTOR: " + this.type.size());
    }

    /**
     *
     * Gets the X
     *
     * @return the type
     */
    public Vector<Integer> getX() throws CoordinateStateException {
        if(checkConsistence())
            return X;
        else throw new CoordinateStateException("MISMATCH VECTOR: " + this.X.size());

    }

    /**

     Gets the x-coordinate of the coordinate at the specified index.
     @param i the index of the coordinate
     @return the x-coordinate of the coordinate at the specified index
     @throws CoordinateStateException if the index is out of bounds and Mismatch Vector
     */
    public int getXByIndex(int i) throws CoordinateStateException {
        if(checkConsistence())
            if(i<=X.size())
                return X.get(i);
            else throw new CoordinateStateException("OutBoundIndex");
        else throw new CoordinateStateException("MISMATCH VECTOR: " + this.X.size());

    }


    /**
     *
     * Gets the Y
     *
     * @return the type
     */
    public Vector<Integer> getY() throws CoordinateStateException {
        if(checkConsistence())
            return Y;
        else throw new CoordinateStateException("MISMATCH VECTOR: " + this.Y.size());
    }
    /**

     Gets the y-coordinate of the coordinate at the specified index.
     @param i the index of the coordinate
     @return the y-coordinate of the coordinate at the specified index
     @throws CoordinateStateException if the index is out of bounds
     */
    public int getYByIndex(int i) throws CoordinateStateException {
        if(checkConsistence())
            if(i<=Y.size())
                return Y.get(i);
            else throw new CoordinateStateException("OutBoundIndex");
        else throw new CoordinateStateException("MISMATCH VECTOR: " + this.Y.size());
    }

    /**

     Adds a new coordinate to the collection.
     @param x the x-coordinate of the new coordinate
     @param y the y-coordinate of the new coordinate
     @param type the type of the new coordinate
     @throws CoordinateStateException if the x, y, and type vectors are not the same length
     */
    public void addALL(int x,int y,int type){
        this.X.add(x);
        this.Y.add(y);
        this.type.add(type);
    }
    /**

     Adds multiple coordinates to the collection.
     @param X an array of x-coordinates
     @param Y an array of y-coordinates
     @param type an array of types
     @throws CoordinateStateException if the length of X, Y, and type are not the same
     */
    public void bulkADD(int[] X, int[] Y, int[] type) throws CoordinateStateException {
        if((X.length == Y.length)&& (Y.length == type.length)){
            for(int i = 0;i<X.length;i++){
                this.addALL(X[i],Y[i],type[i]);
            }
        }else throw new CoordinateStateException("MISMATCH VECTOR: " + X.length);
    }

    /**
     *
     * Sets the type
     *
     * @param type  the type.
     */
    public void setType(Vector<Integer> type) {

        this.type = type;
    }


    /**
     *
     * Sets the X
     *
     * @param x  the x.
     */
    public void setX(Vector<Integer> x) {

        this.X = x;
    }


    /**
     *
     * Sets the Y
     *
     * @param y  the y.
     */
    public void setY(Vector<Integer> y) {

        this.Y = y;
    }


    /**
     *
     * Equals
     *
     * @param x  the x.
     * @param y  the y.
     * @param type  the type.
     * @param index  the index.
     * @return boolean
     */
    public boolean equals(int x, int y, int type,int index) {
        return (this.type.get(index) == type) && (this.Y.get(index) == y) && (this.X.get(index) == x);
    }

    /**
     *
     * To string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Coordinate{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }

    /**

     Gets the number of coordinates in the collection.
     @return the number of coordinates in the collection
     */
    public Integer size() throws CoordinateStateException {
        if(checkConsistence())
            return X.size();
        else throw new CoordinateStateException("MISMATCH VECTOR: " + this.Y.size());
    }


}
