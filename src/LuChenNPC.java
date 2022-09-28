import java.util.Locale;
import java.util.Scanner;

public class LuChenNPC extends NPC{
    String usrN;
    boolean addItem;


    public LuChenNPC(String userName) {
        this.usrN=userName;


    }
    /***
     * This method allows the player to interact with the NPC LuChen.
     * @return null
     * @author ZHUOJUN XIAO
     */

    public void chat(){
        System.out.println( usrN+" , good to see you! \n" +
                "I heard from the prophet that a warrior will defeat Galakrond and bring peace to the village. \n" +
                "Are you the one chosen by the prophet?\n" +
                "\n" +
                " If you can solve this puzzle, I will give you a reward.\n" +
                "\n" +
                "What is always in front of you but can’t be seen?\n" +
                "A. Air\n" +
                "B. Future\n");

        Scanner scanner=new Scanner(System.in);

        String ans=scanner.nextLine();
        ans=ans.toUpperCase(Locale.ROOT);

        checkAnswer(ans);

    }

    /***
     * This method is used to check if the player's answer is accurate;
     * @param answer Answers entered by players.
     * @author ZHUOJUN XIAO
     */
    public void checkAnswer(String answer){

        addItem= answer.equals("B");

    }


    /***
     * This method checks if a reward can be given to the player.
     * @return true if the player's answer is correct.
     * @author ZHUOJUN XIAO
     */
    public boolean getAddItem(){
        return addItem;
    }



}

