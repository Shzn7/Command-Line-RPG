import java.util.Locale;
import java.util.Scanner;

public class WuKongNPC extends NPC {
    String usrN;
    boolean addItem;


    public WuKongNPC(String userName) {
        this.usrN=userName;


    }

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

    public void checkAnswer(String answer){

        addItem= answer.equals("D");

    }

    public boolean getAddItem(){
        return addItem;
    }


}
