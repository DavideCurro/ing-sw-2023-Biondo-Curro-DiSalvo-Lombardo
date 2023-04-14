package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.commonStrategy.*;
import it.polimi.ingsw.model.exception.PlaygroundException;
import it.polimi.ingsw.model.personalStrategy.GoalP1;
import it.polimi.ingsw.model.personalStrategy.PersonalObj;
import it.polimi.ingsw.model.player.Player;

import java.util.*;

/**
 * Hello world!
 *
 */
public class Match {
    private static final Map<Integer,Integer> pointOBJ2player = Map.of(1,8,2,4);
    private static final Map<Integer,Integer> pointOBJ3player = Map.of(1,8,2,6,3,4);
    private static final Map<Integer,Integer> pointOBJ4player = Map.of(1,8,2,4,3,4,4,2);
    private static Playground p = new Playground();
    private final ObjectiveCommonEXEC o ;
    private int objCount = 0;
    private Queue<Player> players;

    public Match(CommonObj o){
        this.players = new LinkedList<>();
        this.o = new ObjectiveCommonEXEC(o);
    }
    public Match(Queue<Player>p, CommonObj o){
        this.players = p;
        this.o = new ObjectiveCommonEXEC(o);
    }
    private static int pointsCheck(int numPlayersDone,Map<Integer,Integer> pointOBJPlayer){
        return pointOBJPlayer.get(numPlayersDone);

    }
    private static void pointSetter(int objCount, Player nowPlaying){
        switch (p.getNum_players()) {
            case 2 -> nowPlaying.setPoints(nowPlaying.getPoints() + pointsCheck(objCount, pointOBJ2player));
            case 3 -> nowPlaying.setPoints(nowPlaying.getPoints() + pointsCheck(objCount, pointOBJ3player));
            case 4 -> nowPlaying.setPoints(nowPlaying.getPoints() + pointsCheck(objCount, pointOBJ4player));
            default -> throw new IllegalStateException("Unexpected value: " + p.getNum_players());
        }


    }

    private PersonalObj personalOBJChooser() throws MatchExeception{
        Random random = new Random();
        return switch (random.nextInt(12) + 1) { //TODO: Expand
            case 1 -> new GoalP1();
            case 2 -> new GoalP2();
            case 3 -> new GoalP3();
            case 4 -> new GoalP4();
            case 5 -> new GoalP5();
            case 6 -> new GoalP6();
            case 7 -> new GoalP7();
            case 8 -> new GoalP8();
            case 9 -> new GoalP9();
            case 10 -> new GoalP10();
            case 11 -> new GoalP11();
            case 12 -> new GoalP12();
            default -> throw new RuntimeException("ERRORE generazione persona OBJ");
        };
    }
    //JUST TEST
    public void main( String[] args ) {

        this.newPlayer("Claudio");
        this.newPlayer("Sibb");
        this.newPlayer("Mati");

        this.setupPlayground();
        p.printOutPlayground();

        this.game();

    }
    public void newPlayer(String nick){
        players.add(new Player(personalOBJChooser(),nick));
    }
    public void setupPlayground(){
        try {
            p = new Playground(players.size());
        }catch (PlaygroundException e){
            System.out.println(e.getMessage());
        }
    }
    public void game(){
        Player nowPlaying;
        while(true){
            nowPlaying = players.remove();
            players.add(nowPlaying);
            try{
                if(nowPlaying.pickUp(p)) {
                    if(o.execCheck(nowPlaying)){
                        pointSetter(objCount,nowPlaying);
                        objCount++;
                    }
                    // personal obj still missing
                    //nowPlaying.checkPersonalOBJ
                    if (nowPlaying.getMy_shelfie().isFull())
                        break;
                }p.countSelected();
            }catch (RuntimeException | PlaygroundException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}



