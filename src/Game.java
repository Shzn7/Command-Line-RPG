import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static final String JSON_FILE = "src/gameConfiguration.json";
    private static User user;

    /**
     * Variables to store all game configuration data
     */
    private static String gamePath  = "";
    private static String encounterPath  = "";
    private static String encounterType  = "";



    // This is a temporary role created for development purposes and can be deleted after development once the role creation function has been completed.
    static User bob = new User("bob", "Wizard", 100, 0, new ArrayList<Item>() );


    /**
     * Index to store the place along the gameString the user is at.
     */
    static int index = 0;

    public static void main(String[] args) {
        File gameConfigFile = new File(JSON_FILE);
        LoadGameConfiguration gameConfig = new LoadGameConfiguration().setGameConfigFromJsonFile(gameConfigFile);

        System.out.println("Game Path     : " + gameConfig.getGamePath());
        System.out.println("Encounter Path: " + gameConfig.getEncounterPath());
        System.out.println("Encounter Type: " + gameConfig.getEncounterType());

        selectCharacter();

        gamePath =  gameConfig.getGamePath();
        encounterPath = gameConfig.getEncounterPath();
        encounterType = gameConfig.getEncounterType();

        move();
    }

    /**
     * Called the first time a user runs the game.
     * Allows user to choose the character they want to play as.
     */
    public static void selectCharacter() {

        System.out.println("\nPick your character:");
        System.out.println("a) Ninja");
        System.out.println("  HP: 95 Attacks: Nunchucks, Punch, Kick");
        System.out.println("b) Wizard");
        System.out.println("  HP: 100 Attacks: Wand, Cat, Potion");
        System.out.println("c) Pirate");
        System.out.println("  HP: 85  Attacks: Sword, Pistol, Small Rock");

        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        switch (input.toUpperCase()) {
            case "A", "NINJA" -> {
                System.out.println("you picked NINJA");
                user = new User("", "Ninja", 95, 0, new ArrayList<>(Arrays.asList(new Nunchucks(), new Punch(), new Kick())));
            }
            case "B", "WIZARD" -> {
                System.out.println("you picked WIZARD");
                user = new User("", "Wizard", 100, 0, new ArrayList<>(Arrays.asList(new Wand(), new FireBall(), new Poison())));
            }
            case "C", "PIRATE" -> {
                System.out.println("you picked PIRATE");
                user = new User("", "Pirate", 85, 0, new ArrayList<>(Arrays.asList(new Sword(), new Pistol(), new SmallRock())));
            }
            default -> {
                System.out.println("Not a valid input try again");
                selectCharacter();
            }
        }

        gamePath =  gameConfig.getGamePath();
        encounterPath = gameConfig.getEncounterPath();
        encounterType = gameConfig.getEncounterType();

        move();
    }

    public static void move(){

        while(true) {
            /*
            * Will end the game once the user has reached the end of the gameString
             */
            Boolean inputCorrect = false;
            if (index >= gamePath.length()) {
                System.out.println("You made it to the end, congrats!");
                break;
            }

            Scanner read = new Scanner(System.in);
            String input = read.nextLine();


            /*
             * Uses CommandHandler to interpret the command from the input text.
             */
            CommandsEnum command = new CommandHandler().processCommand(input);

            if (command.equals(CommandsEnum.QUIT)) {break;}

            //Temporary outlets for SAVE, LOAD, HELP, STATS
            if (command.equals(CommandsEnum.SAVE) || command.equals(CommandsEnum.LOAD)
                    || command.equals(CommandsEnum.HELP) || command.equals(CommandsEnum.STATS)) {
                System.out.println(input + " is under construction, please try again.");}

            //Display the user inventory's Items
            if(command.equals(CommandsEnum.INV)){
                bob.displayInventory();
                inputCorrect = true;
            }



            if (command.equals(CommandsEnum.NULL)) {
                System.out.println(input + " was an invalid input, please try again.");
                inputCorrect = true;

            }

            /*
             * Once it is determined that the user submitted a valid string, the program will find the current CommandEnum
             * value of the gamePath, encounterPath and encounterType path, to determine whether the user moves forward, encounters
             * an object or goes the wrong way.
             */

            CommandsEnum correctPath = (new CommandHandler().processCommand(gamePath.charAt(index) + ""));
            CommandsEnum eventPath = (new CommandHandler().processCommand(encounterPath.charAt(index) + ""));
            CommandsEnum eventType = (new CommandHandler().processEncounter(encounterType.charAt(index)));

            if (!inputCorrect) {
                if (command.equals(correctPath)) {
                    index++;
                    System.out.println(input + " was the correct path, you continue on your journey.");
                } else {
                    if (command.equals(eventPath)) {

                        switch (eventPath) {
                            case ENEMY -> Interactions.battle();
                            case NPC -> Interactions.talkWithNPC();
                            case REWARD -> bob.addRandomItem();
                        }

                        System.out.println(input + " was the encounter path, you would have a " + eventType);
                    } else {
                        System.out.println(input + " is the wrong way! Try again.");
                    }
                }
            }

        }
    }

}
