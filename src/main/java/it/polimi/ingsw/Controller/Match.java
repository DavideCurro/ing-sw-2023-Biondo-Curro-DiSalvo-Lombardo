package it.polimi.ingsw.Controller;


import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.CommonStrategy.*;
import it.polimi.ingsw.Model.Exception.CoordinateStateException;
import it.polimi.ingsw.Model.Exception.PlaygroundException;
import it.polimi.ingsw.Model.PersonalStrategy.*;
import it.polimi.ingsw.Model.PersonalStrategy.PersonalObj;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Utility.VirtualView;

import java.io.Serializable;
import java.util.*;

/**
 * Hello world!
 *
 */
public class Match implements Serializable {
    private static final Map<Integer,Integer> pointOBJ2player = Map.of(1,8,2,4);
    private static final Map<Integer,Integer> pointOBJ3player = Map.of(1,8,2,6,3,4);
    private static final Map<Integer,Integer> pointOBJ4player = Map.of(1,8,2,4,3,4,4,2);
    private static Playground p;
    private ObjectiveCommonEXEC o1 ;
    private ObjectiveCommonEXEC o2;
    private int objCount1 = 1;
    private  int objCount2 = 1;
    private static boolean thrown = false;
    private LinkedList<Player> players;

    /**
     * It's a constructor
     */
    public Match(){
        players = new LinkedList<>();

       // this.o = new ObjectiveCommonEXEC(o);
    }
    public Match(LinkedList<Player>p, CommonObj o1, CommonObj o2){
        players = p;
        this.o1 = new ObjectiveCommonEXEC(o1);
        this.o2 = new ObjectiveCommonEXEC(o2);
    }

    /**
     * Set the common objective
     * @param o1 CommonOBJ
     * @param o2 CommonOBJ
     */
    public boolean setObjectiveCommonEXEC(CommonObj o1, CommonObj o2){
        if(o1.getType() != o2.getType()) {
            this.o1 = new ObjectiveCommonEXEC(o1);
            this.o2 = new ObjectiveCommonEXEC(o2);
            return true;
        }
        return false;
    }

    /**
     * retrieve from a map how many points should assign
     * @param numPlayersDone, how many people made this goal
     * @param pointOBJPlayer, depends on how many player is the match
     * @return int that represent the point made
     */

    private static int pointsCheck(int numPlayersDone,Map<Integer,Integer> pointOBJPlayer){
        return pointOBJPlayer.get(numPlayersDone);

    }
    public CommonObj getCommonOBJ1(){
        return o1.getCommonObj();
    }
    public CommonObj getCommonOBJ2(){
        return o2.getCommonObj();
    }

    /**
     * assign point to current player
     * @param objCount how many people made this goal
     * @param nowPlaying who made the goal
     */
    public static void pointSetter(int objCount, Player nowPlaying){
        switch (p.getNum_players()) {
            case 2 -> nowPlaying.setPublicPoints(nowPlaying.getPublicPoints() + pointsCheck(objCount, pointOBJ2player));
            case 3 -> nowPlaying.setPublicPoints(nowPlaying.getPublicPoints()  + pointsCheck(objCount, pointOBJ3player));
            case 4 -> nowPlaying.setPublicPoints(nowPlaying.getPublicPoints()  + pointsCheck(objCount, pointOBJ4player));
            default -> throw new IllegalStateException("Unexpected value: " + p.getNum_players());
        }
    }

    /**
     * Random select the personal goal
     * @return personal goal
     * @throws MatchExeception, if random fails
     */
    public PersonalObj personalOBJChooser() throws MatchExeception{
        Random random = new Random();
        return switch (random.nextInt(12)+1) {
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
            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(12) + 1);
        };
    }


    /**
     * Random select the common goal
     * @return common goal
     * @throws MatchExeception, if random fails
     */
    private CommonObj CommonOBJChooser() throws MatchExeception{
        Random random = new Random();
        return switch (random.nextInt(12)+1) {
            case 1 -> new GoalC1();
            case 2 -> new GoalC2();
            case 3 -> new GoalC3();
            case 4 -> new GoalC4();
            case 5 -> new GoalC5();
            case 6 -> new GoalC6();
            case 7 -> new GoalC7();
            case 8 -> new GoalC8();
            case 9 -> new GoalC9();
            case 10 -> new GoalC10();
            case 11 -> new GoalC11();
            case 12 -> new GoalC12();
            default -> throw new MatchExeception("ERROR generation common OBJ");
        };
    }


    /**
     * Handle the joining of a new player in the game
     * @param nick of player
     * @throws MatchExeception, some error occurs on personal or common objective appear
     */
    public void newPlayer(String nick) throws  MatchExeception{
        if(players.isEmpty()){
            players.add(new Player(personalOBJChooser(),nick,false));
            VirtualView.printPersonalOBJ(players.getLast());
           if(! setObjectiveCommonEXEC(CommonOBJChooser(),CommonOBJChooser()))
               while(!setObjectiveCommonEXEC(CommonOBJChooser(),CommonOBJChooser())) {
                   setObjectiveCommonEXEC(CommonOBJChooser(),CommonOBJChooser());
               }
            return;
        }
        if(players.size()<4) {
            try {
                if(players.size() == 1)
                    players.add(new Player(personalOBJChooser(), nick, true));
                else players.add(new Player(personalOBJChooser(),nick,false));
            } catch (MatchExeception exception) {
                throw new MatchExeception(exception.getMessage());
            }
        }else {
            throw new MatchExeception("Max Player reached");
        }
        VirtualView.printPersonalOBJ(players.getLast());
    }

    /**
     * Just initialize playground
     */
    public void setupPlayground(){
        try {
            p = new Playground(players.size());
        }catch (PlaygroundException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handle new turn
     * @param column in which column should appear the tiles
     * @param picked tiles picked
     * @return 0 if everything went well, 1 otherwise
     * @throws RuntimeException, if some error occur while pickup
     */
    public int newTurn(int column, Vector<Tiles> picked) throws RuntimeException{
        Player nowPlaying = players.remove();
        players.addLast(nowPlaying);
        thrown = false;
        try{
            if(nowPlaying.pickUp(p,column,picked)) {
                p.countSelected();
                return 0;
            }
        }catch (RuntimeException | PlaygroundException |CoordinateStateException e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return 1;
    }
    public void resetPlayers(){
        Player tmp = players.removeLast();
        players.addFirst(tmp);
        System.out.println(players);
        thrown = true;
    }
    /**
     *  get playground
     * @return Playground
     */
    public Playground getP() {
        return p;
    }

    /**
     * Return who played last
     * @return player
     */
    public Player getLastPlayer(){

        return thrown ? players.peekFirst() : players.peekLast();
    }

    /**
     * Return all player
     * @return players
     */
    public  LinkedList<Player> getPlayer(){
        return players;
    }
    public Player getThisPlayer(String nick){
        for(Player player : players){
            if(player.getNickname().equals(nick)){
                return player;
            }
        }
        return null;
    }


    public Player getNowPlaying(){
        return players.peekFirst();
    }
    /**
     * Test the common goal
     * @param nowPlaying, who is playing
     * @return the result[0]=1 means the goal has been passed, result[1] represent how many people made this
     */
    public int[] commonOBJTesting(Player nowPlaying){
        int[] result = new int[3];
        result[0] = -1;
        result[1] = -1;
        result[2] = -1;
        if(!nowPlaying.isHasMadeCommonOBJ1()) {
            if (o1.execCheck(nowPlaying)) {
                pointSetter(objCount1, nowPlaying);
                objCount1++;
                result[0] = 1;
                result[1] = objCount1;
                nowPlaying.setHasMadeCommonOBJ1(true);
            }
        }
        if(!nowPlaying.isHasMadeCommonOBJ2()){
            if(o2.execCheck(nowPlaying)){
                pointSetter(objCount2, nowPlaying);
                objCount2++;
                if(result[0] == -1){
                    result[0] = 2;
                }else{
                    result[0] = 3;
                }
                result[2] = objCount2;
                nowPlaying.setHasMadeCommonOBJ2(true);
            }
        }
        return result;

    }
    public int detectEndGame(){
        if(getLastPlayer().getIs_second()){
            return 0;
        }else{
            for(int i = 0; i<players.size()-1;i++){
                if(players.get(i).getIs_second())
                    return i;
            }
        }
        return -1;
    }
    public Player getWinner(){
        Player winner = new Player();
        for(Player player : players){
            if(player.getPoints() > winner.getPoints())
                winner = player;
        }
        return winner;
    }
    public void calculateADJ(){
        for(Player player : players){
            Vector<Integer> count = player.calculateADJ();
            for(Integer point : count){
                player.addPoint(point);
            }
        }
    }


}



