package it.polimi.ingsw.objective;

import it.polimi.ingsw.Tiles;

public class ObjCommon extends Objective{
    protected ObjCommon(int points, int[] deck, Tiles[][] ground) {
        super(points, deck, ground);
    }

    @Override
    protected Cards cardsFactory() {
        return new CommonCards();
    }
}
