package it.polimi.ingsw.NotWorking.GUI.Controllers;

public enum Scenes {
    SETUP("SETUP", "/setup.fxml"),

    MENU("MENU", "/mainMenu.fxml");
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
