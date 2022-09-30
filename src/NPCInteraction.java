import java.util.Locale;
import java.util.Scanner;

public class NPCInteraction {

    static User user;


    /***
     * This method enables player and npc interaction in Game;
     * The npc will talk to the player and give the player a puzzle that will give the player a random reward if they can solve it
     * @author ZHUOJUN XIAO
     * @param givenUser user's name
     * @param amountOfCalls Count the number of times the player has encountered an NPC, making sure it is different each time.
     */
    public static void talkWithNPC(User givenUser, int amountOfCalls) {
        //The first NPC the player will encounter.
        System.out.println(Game.DEFAULT_LINE_BREAK);
        if (amountOfCalls==1){
            user=givenUser;
            JosephaNPC josephaNPC=new JosephaNPC(user.getUserName());
            josephaNPC.chat();
            System.out.println("\n");
            user.addRandomItem();

        }
        //The Second NPC the player will encounter.
        if(amountOfCalls==2) {
            user = givenUser;

            LuChenNPC luchenNPC = new LuChenNPC(user.getUserName());
            luchenNPC.chat();
            if (luchenNPC.getAddItem()) {
                System.out.println("Your answer is correct\n");
                System.out.println();
                user.addRandomItem();

            } else {
                System.out.println("Your answer is incorrect\n");
            }
        }

        //The Final NPC the player will encounter.
        if (amountOfCalls==3){
            user=givenUser;
            WuKongNPC wuKongNPC=new WuKongNPC(user.getUserName());
            wuKongNPC.chat();

            if(wuKongNPC.getAddItem()){
                System.out.println("Your answer is correct\n");
                System.out.println();
                user.addRandomItem();

            }else {
                System.out.println("Your answer is incorrect\n");
            }

        }
        if (amountOfCalls==4){
            System.out.println("You found a lot of dead bodies of villagers here.\n" +
                    "It seems that this place has experienced a fierce battle.\n"+
                    "Do you want to inspect the body?\n"+
                    "A.Yes\n"+"B.No\n");

            Scanner scanner=new Scanner(System.in);

            String choice=scanner.nextLine();

            choice=choice.toUpperCase(Locale.ROOT);

            if (choice.equals("A")){
                user.addRandomItem();
            }

        }

    }


}
