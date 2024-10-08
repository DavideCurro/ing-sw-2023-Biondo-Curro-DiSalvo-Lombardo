package it.polimi.ingsw.Model.CommonStrategy;

import it.polimi.ingsw.Model.Player.Player;

import java.io.Serializable;

public class ObjectiveCommonEXEC implements Serializable {
        private final CommonObj commonObj;
        public ObjectiveCommonEXEC(CommonObj commonObj) {
            this.commonObj = commonObj;
        }
        public CommonObj getCommonObj(){return commonObj;}
        public boolean execCheck(Player l){
            return commonObj.check(l);
        }

        public int getType(){
            return commonObj.getType();
        }
    }


