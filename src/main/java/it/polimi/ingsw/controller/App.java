package it.polimi.ingsw.controller;
//Rene Ferretti MVC

import it.polimi.ingsw.model.commonStrategy.CommonObj;
import it.polimi.ingsw.model.commonStrategy.GoalC3;

import java.io.IOException;
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



