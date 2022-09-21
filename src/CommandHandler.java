
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * CommandHandler.java class is used to interpret inputs given by the scanner in the move function of game.java
 * as well as the gamePath, encounterPath and encounterType path strings.
 *
 * @author Alex Basserabie
 */

public class CommandHandler {

    /**
     * Variables to store all possible variations of the north, south, east, west and quit inputs,
     * ensuring that users can use either an identifying letter, or three letters to get their desired action.
     */

    List<String> northInputs = Arrays.asList("NORTH", "N", "NOR");
    List<String> southInputs = Arrays.asList("SOUTH", "S", "SOU");
    List<String> westInputs = Arrays.asList("WEST", "W", "WES");
    List<String> eastInputs = Arrays.asList("EAST", "E", "EAS");
    List<String> quitInputs = Arrays.asList("QUIT", "Q");

    public CommandHandler() {
    }

    /**
     * This method take the input string from move and interprets the meaning of the input. If the input fits one of the
     * accepted strings, it is converted into an enum commandsEnum type, for easy conversion later in the program. If
     * the input sting isn't recognised, a CommandEnum.NULL value is returned.
     *
     * @author Alex Basserabie
     *
     * @param string, the input string from Move
     * @return CommandsEnum, an individual command derived from the input string.
     */

    public CommandsEnum processCommand(String string) {
        /*
          Converting the string to uppercase for consistency
         */
        string = string.toUpperCase(Locale.ROOT);

        /*
          Checking if the input string is contained in the list variables of acceptable inputs stored earlier.
         */
        if (northInputs.contains(string)) {return CommandsEnum.NORTH;}
        if (southInputs.contains(string)) {return CommandsEnum.SOUTH;}
        if (westInputs.contains(string)) {return CommandsEnum.WEST;}
        if (eastInputs.contains(string)) {return CommandsEnum.EAST;}
        if (quitInputs.contains(string)) {return CommandsEnum.QUIT;}

        /*
          Utilising the switch statement for commands with only one acceptable string
         */
        switch (string) {
            case "SAVE" -> {return CommandsEnum.SAVE;}
            case "LOAD" -> {return CommandsEnum.LOAD;}
            case "HELP" -> {return CommandsEnum.HELP;}
            case "INV" -> {return CommandsEnum.INV;}
            case "STATS" -> {return CommandsEnum.STATS;}
            default -> {return  CommandsEnum.NULL;}
        }

    }
    /**
     * This method is used for interpreting the encounter types of the encounterType gameString. Will return
     * CommandEnum.NULL value if encounterType isn't correct.
     *
     * @author Alex Basserabie
     *
     * @param input, the character from the encounterType gameString.
     * @return CommandsEnum, an individual command derived from the input character.
     */
    public CommandsEnum processEncounter(char input) {

        switch (input) {
            case 'R' -> {return CommandsEnum.REWARD;}
            case 'N' -> {return CommandsEnum.NPC;}
            case 'E' -> {return CommandsEnum.ENEMY;}
            default -> {return  CommandsEnum.NULL;}
        }
    }

}
