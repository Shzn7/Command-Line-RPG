public class Game {

    public static void main(String[] args) {
        LoadGameConfiguration gameConfig = new LoadGameConfiguration().setGameConfigFromJsonFile();

        System.out.println("Game Path     : " + gameConfig.getGamePath());
        System.out.println("Encounter Path: " + gameConfig.getEncounterPath());
        System.out.println("Encounter Type: " + gameConfig.getEncounterType());
    }

    public void move(){

    }
}
