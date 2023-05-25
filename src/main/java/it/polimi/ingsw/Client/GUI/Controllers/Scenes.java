package it.polimi.ingsw.Client.GUI.Controllers;

public enum Scenes {

    MENU("MENU", "/mainMenu.fxml"),
    SETUP("SETUP", "/setup.fxml");
    //ENDGAME("ENDGAME", "/endGame.fxml");
    private final String name;
    private final String file;
    private Scenes(String name, String file) {
        this.file = file;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFile() {
        return file;
    }
}
