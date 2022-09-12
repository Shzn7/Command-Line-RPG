import java.io.File;

public class Game {
    private static final String JSON_FILE = "src/gameConfiguration.json";

    public static void main(String[] args) {
        File jsonFile = new File(JSON_FILE);
        LoadGameConfiguration gameConfig = new LoadGameConfiguration().setGameConfigFromJsonFile(jsonFile);

        System.out.println("Game Path     : " + gameConfig.getGamePath());
        System.out.println("Encounter Path: " + gameConfig.getEncounterPath());
        System.out.println("Encounter Type: " + gameConfig.getEncounterType());
    }

    public void move(){

    }
}
