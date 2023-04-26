package it.polimi.ingsw.socket;

import java.io.Serializable;

public enum Content implements Serializable {

    NICKNAME,
    NEWGAME,
    PLAYERDATA,
    FAIL,
    PICKTILE,
    PICKEDTILE,
    MATCH
}
