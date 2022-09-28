public class NPCInteraction {

    static User user;

    public static void talkWithNPC(User givenUser, int amountOfCalls) {
        //The first NPC the player will encounter.
        if (amountOfCalls==1){
            user=givenUser;
            JosephaNPC josephaNPC=new JosephaNPC(user.getUserName());
            josephaNPC.chat();
            user.addRandomItem();

        }
        //The Second NPC the player will encounter.
        if(amountOfCalls==2){
        user = givenUser;

        LuChenNPC luchenNPC = new LuChenNPC(user.getUserName());
        luchenNPC.chat();
        if (luchenNPC.getAddItem()) {
            System.out.println("Your answer is correct\n");
            user.addRandomItem();

        }else {
            System.out.println("Your answer is incorrect\n");
        }

        //The Final NPC the player will encounter.
        if (amountOfCalls==3){
            user=givenUser;
            WuKongNPC wuKongNPC=new WuKongNPC(user.getUserName());
            wuKongNPC.chat();

            if(wuKongNPC.getAddItem()){
                System.out.println("Your answer is correct\n");
                user.addRandomItem();

            }else {
                System.out.println("Your answer is incorrect\n");
            }

        }
        }

    }


}
