package it.polimi.ingsw.objective;

import it.polimi.ingsw.Tiles;

public class ObjPersonal extends Objective {
    protected ObjPersonal(int points, int[] deck, Tiles[][] ground) {
        super(points, deck, ground);
    }

    @Override
        protected Cards cardsFactory() {
            return new PersonalCards();
        }
    }

