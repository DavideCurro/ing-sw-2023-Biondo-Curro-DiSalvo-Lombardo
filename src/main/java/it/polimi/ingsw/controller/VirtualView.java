package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;

public class VirtualView {


    public VirtualView() {
    }


    public void execeptionOccurred(MatchExeception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
    }
    public void printPlayground(){
        printOutPlayground(Match.getP());
    }
    public void printPlayerLibrary(){
        printOutLibrary(Match.getLastPlayer());
    }
    /**
     *
     * Print output playground
     *
     */
    private void printOutPlayground(Playground ground) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(ground.getGround()[i][j].getType() + "\t");
            }
            System.out.println();
        }
    }

    /**
     *
     * Print output*
     *
     */
    private void printOutLibrary(Player player) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(player.getMy_shelfie().getShelf()[i][j].getType() + "\t");
            }
            System.out.println();
        }
    }
    public void printOutPointsPerPlayer(Player player){
        System.out.println(player.getNickname() + " achieved : "+ player.getPoints()+" points" );
    }
}
