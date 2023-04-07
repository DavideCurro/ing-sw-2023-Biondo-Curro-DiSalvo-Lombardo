package it.polimi.ingsw;

import java.util.Vector;
import java.util.*;
public class Coordinate {
    Vector<Integer> X;
    Vector<Integer> Y;
    Vector<Integer> type;

    public Coordinate() {
        this.X = new Vector<Integer>();
        this.Y = new Vector<Integer>();
        this.type = new Vector<Integer>();
    }

    public Coordinate(Vector<Integer> x, Vector<Integer> y, Vector<Integer> type) {
        this.X = x;
        this.Y = y;
        this.type = type;
    }


    public boolean equals(int x, int y, int type,int index) {
        return (this.type.get(index) == type) && (this.Y.get(index) == y) && (this.X.get(index) == x);
    }

    public Vector<Integer> getType() {
        return type;
    }

    public void setType(Vector<Integer> type) {
        this.type = type;
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
