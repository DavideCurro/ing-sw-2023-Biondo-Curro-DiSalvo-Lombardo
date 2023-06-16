package it.polimi.ingsw.Utility.Message;

import java.io.Serializable;

public enum Content implements Serializable {

    NICKNAME,
    NEWGAME,
    PLAYERDATA,
    FAIL,
    PICKTILE,
    PICKEDTILE,
    PICKUPFAIL,
    PERSONALOBJDONE,
    COMMONOBJDONE,
    NICKNAME_DUPLICATE,
    WRONG_PLAYER,
    COMMONOBJ,
    ENDGAME,
    StartGame
}
