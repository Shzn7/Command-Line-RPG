import java.util.Locale;
import java.util.Scanner;

public class NPC {
    //Every npc can talk to the player
    public void chat(){

    }

    //Check if the player has successfully solved the puzzle
    public void checkAnswer(String answer){

    }
}

class JosephaNPC extends NPC{
    String usrN;

    public JosephaNPC(String userName) {
        this.usrN=userName;

    }

    /***
     * This method enables the player to talk to NPC Josepha.
     * @return null
     * @author ZHUOJUN XIAO
     */
    public void chat(){
        System.out.println("Hello "+usrN+"\n"+
                "The dragon Galakrond has invaded the village with his army of demons.\n" +
                "The village was plunged into war and chaos.\n" +
                "Please take up your weapons and lead the villagers in the fight against the demon army and slay the evil dragon! \n" +
                "I will give you a reward to help you.");


    }


}

 class LuChenNPC extends NPC{
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

class WuKongNPC extends NPC {
    String usrN;
    boolean addItem;


    public WuKongNPC(String userName) {
        this.usrN=userName;


    }

    /***
     * This method allows the player to interact with the NPC WuKong.
     * @return null
     * @author ZHUOJUN XIAO
     */
    public void chat(){
        System.out.println( "The dragon Galakrond is a very fearsome being.\n" +
                "A number of our warriors have fallen in battle.\n" +
                "I believe you can defeat him! Good luck!\n" +
                "If you can solve this puzzle, I will give you a reward.\n" +
                "What comes once in a minute, twice in a moment, but never in a thousand years?\n" +
                " \n" +
                "A. Thirty-one seconds\n" +
                " \n" +
                "B.1/1000 of a decade\n" +
                " \n" +
                "C. One-tenth of a century\n" +
                " \n" +
                "D.The letter 'M'\n");

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

        addItem= answer.equals("D");

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
