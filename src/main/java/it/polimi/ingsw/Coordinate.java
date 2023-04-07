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
        if(checkConsistence())
            return type;
        else throw new IllegalStateException("MISMATCH VECTOR: " + this.type.size());
    }
    public int getTypeByIndex(int i) {
        if(checkConsistence())
            return type.get(i);
        else throw new IllegalStateException("MISMATCH VECTOR: " + this.type.size());
    }


    public void setType(Vector<Integer> type) {
        this.type = type;
    }
    public void addALL(int x,int y,int type){
        this.X.add(x);
        this.Y.add(y);
        this.type.add(type);
    }
    public Vector<Integer> getX() {
        if(checkConsistence())
            return X;
        else throw new IllegalStateException("MISMATCH VECTOR: " + this.X.size());

    }
    public int getXByIndex(int i) {
        if(checkConsistence())
            return X.get(i);
        else throw new IllegalStateException("MISMATCH VECTOR: " + this.X.size());

    }

    public void setX(Vector<Integer> x) {
        X = x;
    }

    public Vector<Integer> getY() {
        if(checkConsistence())
            return Y;
        else throw new IllegalStateException("MISMATCH VECTOR: " + this.Y.size());
    }
    public int getYByIndex(int i) {
        if(checkConsistence())
            return Y.get(i);
        else throw new IllegalStateException("MISMATCH VECTOR: " + this.Y.size());
    }
    private boolean checkConsistence(){
        return (this.type.size() == this.Y.size()) &&(this.Y.size() == this.X.size());
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
        if(checkConsistence())
            return X.size();
        else throw new IllegalStateException("MISMATCH VECTOR: " + this.Y.size());
    }


    public void bulkADD(int[] X, int[] Y, int[] type){
        if((X.length == Y.length)&& (Y.length == type.length)){
            for(int i = 0;i<X.length;i++){
                this.addALL(X[i],Y[i],type[i]);
            }
        }else throw new IllegalStateException("MISMATCH VECTOR: " + X.length);
    }
}
