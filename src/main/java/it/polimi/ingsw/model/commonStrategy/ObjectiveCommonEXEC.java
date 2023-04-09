package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

public class ObjectiveCommonEXEC {
        private CommonObj commonObj;
        public ObjectiveCommonEXEC(CommonObj commonObj) {
            this.commonObj = commonObj;
        }
        public boolean execCheck(Player l){
            return commonObj.check(l);
        }

    }


