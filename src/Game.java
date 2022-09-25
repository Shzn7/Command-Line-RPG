import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private static final String JSON_FILE = "src/gameConfiguration.json";
    private static User user;
    private static SaveLoad saveLoadHandler;

    /**
     * Variables to store all game configuration data
     */
    private static String gamePath  = "";
    private static String encounterPath  = "";
    private static String encounterType  = "";


    /**
     * Index to store the place along the gameString the user is at.
     */
    static int index = 0;

    public static void main(String[] args) {
        File gameConfigFile = new File(JSON_FILE);
        LoadGameConfiguration gameConfig = new LoadGameConfiguration().setGameConfigFromJsonFile(gameConfigFile);
        saveLoadHandler = new SaveLoad();

        System.out.println("Game Path     : " + gameConfig.getGamePath());
        System.out.println("Encounter Path: " + gameConfig.getEncounterPath());
        System.out.println("Encounter Type: " + gameConfig.getEncounterType());

        // Asking the user if they would like to load from save file
        boolean didLoad = handleLoadRequest();

        // if the user decided not to load from save game, run through normal setup
        if(!didLoad)
        {
            setupCharacter();
            // Setting our username
            System.out.println("\nChoose your username:");
            Scanner read = new Scanner(System.in);
            String username = read.nextLine();
            user.setUserName(username);
            System.out.println("Your username is "+user.userName);

            gamePath =  gameConfig.getGamePath();
            encounterPath = gameConfig.getEncounterPath();
            encounterType = gameConfig.getEncounterType();
        }

        move();
    }

    /**
     * Function to handle the user requesting to load from a save game
     * after interpreting whether they would like to.
     *
     * @return boolean for whether the user did (true) or did not (false) load.
     *
     * @author Brad Froud (u7285455)
     */
    private static boolean handleLoadRequest()
    {
        System.out.println("\nLoad from save file? (Y/N)");
        Scanner read = new Scanner(System.in);
        String loadResponse = read.nextLine();

        switch (loadResponse.toUpperCase())
        {
            case "Y", "YES" ->
            {
                System.out.println("\nLoading...");
                // do the loading stuff
                System.out.println("Done!");
                return true;
            }
            case "N", "NO" ->
            {
                System.out.println("\nContinuing to new game...");
                return false;
            }
            default ->
            {
                System.out.println("\nNot a valid input. Please try again.");
                handleLoadRequest();
            }
        }
        return false;
    }

    /**
     * Called the first time a user runs the game.
     * Allows user to choose the character they want to play as.
     * @author Deni Lanc
     */
    public static void setupCharacter() {

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
                user = new User(0);
            }
            case "B", "WIZARD" -> {
                System.out.println("you picked WIZARD");
                user = new User(1);
            }
            case "C", "PIRATE" -> {
                System.out.println("you picked PIRATE");
                user = new User(2);
            }
            default -> {
                System.out.println("Not a valid input try again");
                setupCharacter();
            }
        }
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
            if (command.equals(CommandsEnum.LOAD)
                    || command.equals(CommandsEnum.HELP) || command.equals(CommandsEnum.STATS)) {
                System.out.println(input + " is under construction, please try again.");}

            // Save the game to file
            if(command.equals(CommandsEnum.SAVE))
            {
                System.out.println("\nSaving...");
                File gameConfigFile = new File(JSON_FILE);
                saveLoadHandler.saveGameStateToFile(new LoadGameConfiguration().setGameConfigFromJsonFile(gameConfigFile), index, user);
                inputCorrect = true;
                System.out.println("Done!");
            }

            // Load the game from file
            if(command.equals(CommandsEnum.LOAD))
            {
                System.out.println("\nLoading...");
                //saveLoadHandler.loadGameStateFromFile(this);
                //TODO
                inputCorrect = true;
                System.out.println("Done!");
            }

            //Display the user inventory's Items
            if(command.equals(CommandsEnum.INV)){
                user.displayInventory();
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
                            case REWARD -> user.addRandomItem();
                        }

                        System.out.println(input + " was the encounter path, you would have a " + eventType);
                    } else {
                        System.out.println(input + " is the wrong way! Try again.");
                    }
                }
            }

        }
    }

    /**
     * Setter method to set the correctGamePath
     * @author Brad Froud (u7285455)
     */
    public void setGamePath(String input) { gamePath = input; }

    /**
     * Setter method to set the encounterPath
     * @author Brad Froud (u7285455)
     */
    public void setEncounterPath(String input) {
        encounterPath = input;
    }

    /**
     * Setter method to set the encounterType
     * @author Brad Froud (u7285455)
     */
    public void setEncounterType(String input) {
        encounterType = input;
    }

}
