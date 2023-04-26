package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Playground.Tiles;

import java.util.Vector;

public class Controller {
    private Match match;
    private int numPlayer;
    private VirtualView view;

    public Controller(Match match, VirtualView view) {
        this.numPlayer = -1;
        this.match = match;
        this.view = view;
    }

    public VirtualView getView() {
        return view;
    }

    public void setView(VirtualView view) {
        this.view = view;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void newPlayer(String nick) {
        try {
            this.match.newPlayer(nick);
        } catch (MatchExeception e) {
            System.out.println(e.getMessage());
        }

    }

    public void startGame() {
        this.match.setupPlayground();
        this.view.printPlayground(this.match.getP());
    }

    public int newTurn(int column, Vector<Tiles> picked) {
        return this.match.newTurn(column, picked);
    }
    public void setNumPlayer(int numPlayer){
        this.numPlayer = numPlayer;
    }
    public int getNumPlayer(){return this.numPlayer;}
}


        /*
    private int validateInput(){

        int tmp ;
        Scanner scanner = new Scanner(System.in);
        try {
            tmp = scanner.nextInt() - 1;
        }catch (java.util.InputMismatchException e){
            scanner.next();
            throw new RuntimeException(e);
        }
        return tmp;

    }
    }*/



