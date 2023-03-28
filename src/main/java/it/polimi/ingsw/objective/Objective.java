package it.polimi.ingsw.objective;

import it.polimi.ingsw.Tiles;

public abstract class Objective {

    private int points;
    private int[] deck;
    private Tiles[][] ground;

    protected Objective(int points, int deck[], Tiles[][] ground) {
        this.points = points;
        this.deck = deck;
        this.ground = ground;
    }

    public void shuffleCards(){
        for (int i = 0; i < deck.length; i++) {
            int j = (int)(Math.random() * deck.length); // Get a random index out of 12
            int temp = deck[i]; // Swap the cards
            deck[i] = deck[j];
            deck[j] = temp;
        }

    }

    public final Cards createCards(){
        // Create Cards (depending on the type of Objective)

        Cards myCards = cardsFactory();

        return myCards;
    }
    protected abstract Cards cardsFactory();

}
