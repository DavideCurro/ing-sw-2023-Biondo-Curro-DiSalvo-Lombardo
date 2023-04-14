package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.commonStrategy.CommonObj;
import it.polimi.ingsw.model.commonStrategy.GoalC3;

public class App {
    private static CommonObj commonObjChooser(){
        return new GoalC3();
    }
    public static void main(String[] args){
        Match model = new Match();
        model.setObjectiveCommonEXEC(commonObjChooser());

        VirtualView view = new VirtualView();

        Controller controller = new Controller(model,view);


        controller.newPlayer("Claudio");
        controller.newPlayer("Sibb");
        controller.startGame();

    }

}



