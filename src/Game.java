import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static final String JSON_FILE = "src/gameConfiguration.json";
    private static User user;

    public static void main(String[] args) {
        File gameConfigFile = new File(JSON_FILE);
        LoadGameConfiguration gameConfig = new LoadGameConfiguration().setGameConfigFromJsonFile(gameConfigFile);

        System.out.println("Game Path     : " + gameConfig.getGamePath());
        System.out.println("Encounter Path: " + gameConfig.getEncounterPath());
        System.out.println("Encounter Type: " + gameConfig.getEncounterType());

        selectCharacter();
    }

    /**
     * Called the first time a user runs the game.
     * Allows user to choose the character they want to play as.
     */
    public static void selectCharacter() {

        System.out.println("\nPick your character:");
        System.out.println("a) Ninja");
        System.out.println("b) Wizard");
        System.out.println("c) Pirate");

        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        switch (input.toUpperCase()) {
            case "A", "NINJA" -> {
                System.out.println("you picked NINJA");
                user = new User("", "Ninja", 95, 0, null);
            }
            case "B", "WIZARD" -> {
                System.out.println("you picked WIZARD");
                user = new User("", "Wizard", 95, 0, null);
            }
            case "C", "PIRATE" -> {
                System.out.println("you picked PIRATE");
                user = new User("", "Pirate", 95, 0, null);
            }
            default -> {
                System.out.println("Not a valid input try again");
                selectCharacter();
            }
        }
    }

    public void move(){

    }
}
