package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;

public class ObjectiveCommonEXEC implements Serializable {
        private CommonObj commonObj;
        public ObjectiveCommonEXEC(CommonObj commonObj) {
            this.commonObj = commonObj;
        }
        public boolean execCheck(Player l){
            return commonObj.check(l);
        }

    }


