import java.util.Locale;
import java.util.Scanner;

public class LuChenNPC extends NPC{
    String usrN;
    boolean addItem;


    public LuChenNPC(String userName) {
        this.usrN=userName;


    }

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

    public void checkAnswer(String answer){

        addItem= answer.equals("B");

    }

    public boolean getAddItem(){
        return addItem;
    }



}

