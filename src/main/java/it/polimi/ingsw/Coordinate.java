package it.polimi.ingsw;

import java.util.Vector;
import java.util.*;
public class Coordinate {
    Vector<Integer> X;
    Vector<Integer> Y;

    public Coordinate() {
        this.X = new Vector<Integer>();
        this.Y = new Vector<Integer>();

    }

    /**
     *
     * @param x
     * @param y
     */
    public Coordinate(Vector<Integer> x, Vector<Integer> y) {
        X = x;
        Y = y;

    }
    public Vector<Integer> getX() {
        return X;
    }

    public void setX(Vector<Integer> x) {
        X = x;
    }

    public Vector<Integer> getY() {
        return Y;
    }

    public void setY(Vector<Integer> y) {
        Y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
    public Integer size(){
        return Math.max(X.size(), Y.size());
    }
}
