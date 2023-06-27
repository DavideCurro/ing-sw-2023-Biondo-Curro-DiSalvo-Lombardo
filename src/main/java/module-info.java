module it.polimi.ingsw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.logging;


    opens it.polimi.ingsw.NotWorking.GUI.Controllers to javafx.fxml;
    exports it.polimi.ingsw.NotWorking.GUI.Controllers;
    exports it.polimi.ingsw.Server;
    exports it.polimi.ingsw.NotWorking;
}