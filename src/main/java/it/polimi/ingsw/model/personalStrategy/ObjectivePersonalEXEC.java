package it.polimi.ingsw.model.personalStrategy;

import it.polimi.ingsw.model.player.Player;

public class ObjectivePersonalEXEC {
    private final PersonalObj personalObj;
    public ObjectivePersonalEXEC(PersonalObj personalObj) {
        this.personalObj = personalObj;
    }
    public int execCheck(Player l){
        return personalObj.check(l);
    }
}
