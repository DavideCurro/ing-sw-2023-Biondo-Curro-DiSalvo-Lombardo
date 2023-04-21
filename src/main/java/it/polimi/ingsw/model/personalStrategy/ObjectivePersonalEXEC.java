package it.polimi.ingsw.model.personalStrategy;

import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;

public class ObjectivePersonalEXEC implements Serializable {
    private final PersonalObj personalObj;
    public ObjectivePersonalEXEC(PersonalObj personalObj) {
        this.personalObj = personalObj;
    }
    public int execCheck(Player l){
        return personalObj.check(l);
    }
    public PersonalObj getPersonalObj(){
        return personalObj;
    }
}
