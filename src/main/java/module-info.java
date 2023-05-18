module it.polimi.ingsw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.logging;


    opens it.polimi.ingsw.Client.GUI.Controllers to javafx.fxml;
    exports it.polimi.ingsw.Client.GUI.Controllers;
    exports it.polimi.ingsw.Server;
}