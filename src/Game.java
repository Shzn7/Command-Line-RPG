import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static final String JSON_FILE = "src/gameConfiguration.json";
    public static final String DEFAULT_LINE_BREAK = "----------------------------------------";
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
    private static int index = 0;
    static int NPCCalls=0;


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
        HelpCall.helpCall();
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
                // Initiate the user variable so that loading doesn't crash the game
                user = new User("System", "tempChar", 1, new ArrayList<>(List.of(new Punch())));
                loadHelper();
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

        System.out.println("\nAvailable Characters:");
        System.out.println("a) " + (new User(0)).introPrint());
        System.out.println("b) " + (new User(1)).introPrint());
        System.out.println("c) " + (new User(2)).introPrint());
        System.out.println("\nWhat character would you like to select:");

        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        switch (input.toUpperCase()) {
            case "A", "NINJA" -> {
                System.out.println("You picked NINJA");
                user = new User(0);
            }
            case "B", "WIZARD" -> {
                System.out.println("You picked WIZARD");
                user = new User(1);
            }
            case "C", "PIRATE" -> {
                System.out.println("You picked PIRATE");
                user = new User(2);
            }
            default -> {
                System.out.println("Not a valid input try again");
                setupCharacter();
            }
        }
    }

    /**
     *
     * @authors collaborative effort
     */
    public static void move(){
        //Index variables to ensure the user doesn't redo moves.
    int lastRewardIndex = -1;
    int lastEnemyIndex = -1;
    int lastNPCIndex= -1;

    while(true) {
        System.out.println(DEFAULT_LINE_BREAK);
        if (index >= gamePath.length()) {
            System.out.println("You made it to the end, congrats!");
            break;
        }

        System.out.println("What direction do you want to go? ");

        /*
        * Will end the game once the user has reached the end of the gameString
         */
        Boolean inputCorrect = false;

        Scanner read = new Scanner(System.in);
        String input = read.nextLine();


        /*
         * Uses CommandHandler to interpret the command from the input text.
         */
        CommandsEnum command = new CommandHandler().processCommand(input);

        if (command.equals(CommandsEnum.QUIT)) {break;}

        //Temporary outlets for HELP, STATS
        if (command.equals(CommandsEnum.HELP)){
            HelpCall.helpCall();
            inputCorrect = true;
        }

        // Save the game to file
        if(command.equals(CommandsEnum.SAVE))
        {
            System.out.println("\nSaving...");
            File gameConfigFile = new File(JSON_FILE);
            // the first parameter passed here below should be changed to some form
            // of concatenation of the three path strings if procedurally (or
            // otherwise) generated strings are implemented.
            saveLoadHandler.saveGameStateToFile(new LoadGameConfiguration().setGameConfigFromJsonFile(gameConfigFile), index, user);
            inputCorrect = true;
            System.out.println("Done!");
        }

        // Load the game from file
        if(command.equals(CommandsEnum.LOAD))
        {
            System.out.println("\nLoading...");
            loadHelper();
            inputCorrect = true;
            System.out.println("Done!");
        }

        //Display the user inventory's Items
        if(command.equals(CommandsEnum.INV)){
            user.displayInventory();
            inputCorrect = true;
        }

        //Display the user's statistics
        if(command.equals(CommandsEnum.STATS)){
            System.out.println(user.introPrint());
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
                System.out.println(input + " was the correct step, you continue on your journey.");

            } else {
            if (command.equals(eventPath)) {
                switch (eventType) {
                    case ENEMY -> {
                        if (lastEnemyIndex == index) {
                            System.out.println("You don't want to go back that way!");
                        } else {
                            Interactions.battle(user);
                            lastEnemyIndex = index;
                        }
                    }
                    //When a player meets an NPC, the NPC will talk to the player
                    case NPC ->{
                        if (lastNPCIndex == index) {
                            System.out.println("The NPC walked away, can't go back that direction!");
                        } else {
                            lastNPCIndex =index;
                            NPCCalls++;
                            NPCInteraction.talkWithNPC(user, NPCCalls);
                            }
                        }

                    case REWARD -> {
                        if (lastRewardIndex == index) {
                            System.out.println("You can't have another reward you cheeky bugger!");
                        } else {
                            user.addRandomItem();
                            lastRewardIndex = index;
                        }
                    }
                }
            } else {
                System.out.println(input + " is the wrong way! Try again.");
            }}
        }

        if (user.getHP() <= 0) {break; }

        }
    }

    /**
     * A helper method to load saved data from file using external class
     * then update the appropriate variables in this class.
     *
     * @author Brad Froud (u7285455)
     */
    private static void loadHelper()
    {
        ArrayList<Object> savedGameList = saveLoadHandler.loadGameStateFromFile();
        // Update path variables
        setGamePath((String) savedGameList.get(0));
        setEncounterPath((String) savedGameList.get(1));
        setEncounterType((String) savedGameList.get(2));
        // Update index
        setIndex((int) savedGameList.get(3));
        // Update user variables
        getUser().setUserName((String) savedGameList.get(4));
        getUser().setInventory((List<Item>) savedGameList.get(5));
        getUser().setHP((int) savedGameList.get(6));
        getUser().setCharacterName((String) savedGameList.get(7));
    }

    /**
     * Setter method to set the correctGamePath
     * @author Brad Froud (u7285455)
     */
    public static void setGamePath(String input) { gamePath = input; }

    /**
     * Setter method to set the encounterPath
     * @author Brad Froud (u7285455)
     */
    public static void setEncounterPath(String input) {
        encounterPath = input;
    }

    /**
     * Setter method to set the encounterType
     * @author Brad Froud (u7285455)
     */
    public static void setEncounterType(String input) {
        encounterType = input;
    }

    /**
     * Setter method to set the index/step value
     * @author Brad Froud (u7285455)
     */
    public static void setIndex(int input) {
        index = input;
    }

    /**
     * Getter method to retrieve a reference to the user
     * @author Brad Froud (u7285455)
     */
    public static User getUser() { return user; }

}
