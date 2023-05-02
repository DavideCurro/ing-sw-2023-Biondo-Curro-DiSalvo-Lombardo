package it.polimi.ingsw.Controller;
//Rene Ferretti MVC

import it.polimi.ingsw.Model.CommonStrategy.CommonObj;
import it.polimi.ingsw.Model.CommonStrategy.GoalC3;

import java.io.InputStream;

public class App {
    private static CommonObj commonObjChooser(){
        return new GoalC3();
    }
    public static void main(String[] args){
        InputStream prova = System.in;
        System.setIn(prova);
        Match model = new Match();
        model.setObjectiveCommonEXEC(commonObjChooser());

        VirtualView view = new VirtualView();



    }

}



