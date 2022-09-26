public class HelpCall {

    public static void helpCall() {
        System.out.println(Game.DEFAULT_LINE_BREAK);
        System.out.println("To play the game, enter a direction either north(n), south(s), east(e) or west(w) into the terminal. \n" +
                "• This will allow you to either move forward towards the finish of the maze or possibly encounter some interesting characters. \n" +
                "• You can check your player statistics at any time by entering 'STAT' in the terminal, or 'INV' to see your inventory. \n" +
                "• You can save or load in an existing game by entering either 'save' or 'load' \n" +
                "   and quit the game at any time by entering 'quit' or 'q'.");
    }
}
