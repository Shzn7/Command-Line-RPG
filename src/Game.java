import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static final String JSON_FILE = "src/gameConfiguration.json";

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

            //Temporary outlets for SAVE, LOAD, HELP, INV, STATS
            if (command.equals(CommandsEnum.SAVE) || command.equals(CommandsEnum.LOAD)
                    || command.equals(CommandsEnum.HELP) || command.equals(CommandsEnum.INV) || command.equals(CommandsEnum.STATS)) {
                System.out.println(input + " is under construction, please try again.");}


            if (command.equals(CommandsEnum.NULL)) {
                System.out.println(input + " was an invalid input, please try again.");
            }

            /*
             * Once it is determined that the user submitted a valid string, the program will find the current CommandEnum
             * value of the gamePath, encounterPath and encounterType path, to determine whether the user moves forward, encounters
             * an object or goes the wrong way.
             */

            CommandsEnum correctPath = (new CommandHandler().processCommand(gamePath.charAt(index) + ""));
            CommandsEnum eventPath = (new CommandHandler().processCommand(encounterPath.charAt(index) + ""));
            CommandsEnum eventType = (new CommandHandler().processEncounter(encounterType.charAt(index)));

            if (command.equals(correctPath)) {
                index++;
                System.out.println(input + " was the correct path, you continue on your journey.");
            } else {
                if (command.equals(eventPath)) {

                    switch (eventPath) {
                        case ENEMY -> Interactions.battle();
                        case NPC -> Interactions.talkWithNPC();
                        case REWARD -> Interactions.reward();
                    }

                    System.out.println(input + " was the encounter path, you would have a " + eventType);
                } else {
                    System.out.println(input + " is the wrong way! Try again.");
                }
            }

        }
    }
}
