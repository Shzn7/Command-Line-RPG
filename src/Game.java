import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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


        setupCharacter();
        // Setting our username
        System.out.println("\nChoose your username:");
        Scanner read = new Scanner(System.in);
        String username = read.nextLine();
        user.setUserName(username);
        System.out.println("Your username is "+user.userName);

        gamePath =  "S" + gameConfig.getGamePath();
        encounterPath = "W" + gameConfig.getEncounterPath();
        encounterType = "E" +  gameConfig.getEncounterType();

        move();
    }

    /**
     * Called the first time a user runs the game.
     * Allows user to choose the character they want to play as.
     * @author Deni Lanc
     */
    public static void setupCharacter() {

        System.out.println("\nPick your character:");
        System.out.println("a) " + (new User(0)).introPrint());
        System.out.println("b) " + (new User(1)).introPrint());
        System.out.println("c) " + (new User(2)).introPrint());

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

    public static void move(){
        int lastRewardIndex = -1;
        int lastEnemyIndex = -1;

        while(true) {
            System.out.println("----------------------------------------");
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

            //Temporary outlets for SAVE, LOAD, HELP, STATS
            if (command.equals(CommandsEnum.SAVE) || command.equals(CommandsEnum.LOAD)
                    || command.equals(CommandsEnum.HELP) || command.equals(CommandsEnum.STATS)) {
                System.out.println(input + " is under construction, please try again.");}

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
                    System.out.println(input + " was the correct step, you continue on your journey.");
                    if (index%5 == 0) {
                        user.addRandomItem();
                    }

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
                            case NPC -> Interactions.talkWithNPC();
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
                    }
                }
            }

            if (user.getHP() <= 0) {break; }

        }
    }

}
