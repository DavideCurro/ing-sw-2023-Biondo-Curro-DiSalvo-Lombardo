/*package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.commonStrategy.*;
import it.polimi.ingsw.model.exception.PlaygroundException;
import it.polimi.ingsw.model.player.Player;

import java.util.*;

/**
 * Hello world!
 *

public class App {
    private static Map<Integer,Integer> pointOBJ2player = Map.of(1,8,2,4);
    private static Map<Integer,Integer> pointOBJ3player = Map.of(1,8,2,6,3,4);
    private static Map<Integer,Integer> pointOBJ4player = Map.of(1,8,2,4,3,4,4,2);
    private static int pointsCheck(int numPlayersDone,Map<Integer,Integer> pointOBJPlayer){
        return pointOBJPlayer.get(numPlayersDone);

    }
    private static void pointSetter(Playground p, int objCount, Player nowPlaying){
        switch (p.getNum_players()) {
            case 2 -> {
                nowPlaying.setPoints(nowPlaying.getPoints() + pointsCheck(objCount, pointOBJ2player));
                objCount++;
            }
            case 3 -> {
                nowPlaying.setPoints(nowPlaying.getPoints() + pointsCheck(objCount, pointOBJ3player));
                objCount++;
            }
            case 4 -> {
                nowPlaying.setPoints(nowPlaying.getPoints() + pointsCheck(objCount, pointOBJ4player));
                objCount++;
            }
        }

    }
    public static void main( String[] args ) {
        int objCount = 0;
        Queue<Player> players = new LinkedList<>();
        players.add(new Player(true,"Claudio",true));
        players.add(new Player(false,"Sibb",false));
        players.add(new Player(false,"Mati ",false));
        Playground p = new Playground();
        try {
            p = new Playground(players.size());
        }catch (PlaygroundException e){
            System.out.println(e.getMessage());
        }
        p.printOutPlayground();
        ObjectiveCommonEXEC o = new ObjectiveCommonEXEC(new GoalC3());
        Player nowPlaying;
        while(true){
            nowPlaying = players.remove();
            players.add(nowPlaying);
            try{
                if(nowPlaying.pickUp(p)) {
                    if(o.execCheck(nowPlaying))
                        pointSetter(p,objCount,nowPlaying);
                    // personal obj still missing
                    if (nowPlaying.getMy_shelfie().isFull())
                        break;
                }p.countSelected();
            }catch (RuntimeException | PlaygroundException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
*/


