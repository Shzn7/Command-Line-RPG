import java.io.File;
import java.util.*;

public class Game {
    private static final String JSON_FILE = "src/Saves/gameConfiguration.json";
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

        // Asking the user if they would like to load from save file
        boolean didLoad = handleLoadRequest();

        // if the user decided not to load from save game, run through normal setup
        if(!didLoad)
        {
            System.out.println(DEFAULT_LINE_BREAK);

            // If the user is in survival, they need to pick a character.
            if (gamemode()) {
                System.out.println(DEFAULT_LINE_BREAK);
                setupCharacter();
            }
            System.out.println(DEFAULT_LINE_BREAK);
            // Setting our username
            System.out.println("Choose your username:");
            Scanner read = new Scanner(System.in);
            String username = read.nextLine();
            user.setUserName(username);
            System.out.println("Your username is "+user.userName);

            gamePath =  gameConfig.getGamePath();
            encounterPath = gameConfig.getEncounterPath();
            encounterType = gameConfig.getEncounterType();

            HelpCall.helpCall();
        } else {
            System.out.println(user.introPrint());
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
        System.out.println(DEFAULT_LINE_BREAK);
        System.out.println("Load from save file? (Y/N)");
        Scanner read = new Scanner(System.in);
        String loadResponse = read.nextLine();

        switch (loadResponse.toUpperCase())
        {
            case "Y", "YES" ->
            {
                // Initiate the user variable so that loading doesn't crash the game
                user = new User("System", "tempChar", 1, new ArrayList<>(List.of(new Punch())),GamemodesEnum.SURVIVAL);
                if (!loadHelper()){
                    return false;
                }
                System.out.println("Done!");

                return true;
            }
            case "N", "NO" ->
            {
                System.out.println("Continuing to new game...");
                return false;
            }
            default ->
            {
                System.out.println("Not a valid input. Please try again.");
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

        String input = Scanner_In.Scanner_in() ;
        switch (input.toUpperCase()) {
            case "A", "NINJA" -> user = new User(0);
            case "B", "WIZARD" -> user = new User(1);
            case "C", "PIRATE" -> user = new User(2);

            default -> {
                System.out.println("Not a valid input try again");
                setupCharacter();
            }
        }
        System.out.println("You picked " +user.getCharacterName() +". ");
        System.out.println(DEFAULT_LINE_BREAK);
    }

    /**
     * Allows the user to choose between multiple gamemodes, based on the kind of game play
     * they'd like.
     * @author Alex Basserabie
     * @return boolean; true for survival, false for OP Mode
     */
    public static boolean gamemode() {

        System.out.println("Available Gamemodes:");
        System.out.println("1) Survival, where you have the choice of a few default characters, with limited life and attacks." +
                "\n   For those who want a challenge.");
        System.out.println("2) OP Mode, where you play a character with 1000 HP and all the weapons available. " +
                "\n   For those who think life is hard enough as it is.");

        System.out.println("\nWhat gamemode would you like to select:");

        String input;
        Scanner read = new Scanner(System.in);
        input = read.nextLine();


        switch (input.toUpperCase()) {
            case "1", "SURVIVAL" -> {
                return true;
            }
            case "2", "OP" -> {
                //A list of every attack for the OP Mode Character
                List<Item> everything = new ArrayList<>(Arrays.asList(new EvilThoughts(), new FireBall(),
                        new Headbutt(), new Kick(), new Lawsuit(), new Moan(), new Nunchucks(), new Pistol(), new Punch(), new SelfDrivingCar(),
                        new SmallRock(), new StockMarket(), new Sword(), new TwitterAttack(), new Wand(), new ZombieBite()));


                user = new User("", "OP Character", 1000, everything, GamemodesEnum.OPMODE);
                return false;
            }
            default -> {
                System.out.println("Not a valid input, please try again");
                System.out.println(DEFAULT_LINE_BREAK);
                return gamemode();
            }
        }

    }

    /**
     *
     * @author collaborative effort
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
        boolean inputCorrect = false;

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
    private static boolean loadHelper()
    {
        ArrayList<Object> savedGameList = saveLoadHandler.loadGameStateFromFile();

        if (savedGameList.size() == 0) {
            return false;
        }
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
        return true;
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

class HelpCall {

    public static void helpCall() {
        System.out.println(Game.DEFAULT_LINE_BREAK);
        System.out.println("To play the game, enter a direction either north(n), south(s), east(e) or west(w) into the terminal. \n" +
                "• This will allow you to either move forward towards the finish of the maze or possibly encounter some interesting characters. \n" +
                "• You can check your player statistics at any time by entering 'stats' in the terminal, or 'inv' to see your inventory. \n" +
                "• You can save or load in an existing game by entering either 'save' or 'load' \n" +
                "   and quit the game at any time by entering 'quit' or 'q'.\n" +
                "• Finally, to see this message again, enter 'help'.");
    }
}

enum GamemodesEnum {
    OPMODE, SURVIVAL
}



