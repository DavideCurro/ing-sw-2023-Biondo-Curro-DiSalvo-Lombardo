package it.polimi.ingsw.controller;

public class Controller {
    private Match match;



    private VirtualView view;

    public Controller(Match match, VirtualView view) {
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
    public void newPlayer(String nick){
        try {
            this.match.newPlayer(nick);
        }catch (MatchExeception e){
            System.out.println(e.getMessage());
        }
    }
    public void startGame(){
        this.match.setupPlayground();
        this.view.printPlayground();
        while(this.match.newTurn()== 0){
            this.view.printPlayerLibrary();
            this.view.printOutPointsPerPlayer(Match.getLastPlayer());
            //this.view.//todo: put userinterface in client side
        }

    }


}
