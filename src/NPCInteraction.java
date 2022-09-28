public class NPCInteraction {

    static User user;

    public static boolean talkWithNPC(User givenUser, int amountOfCalls) {
        if (amountOfCalls==1){
            user=givenUser;
            JosephaNPC josephaNPC=new JosephaNPC(user.getUserName());
            josephaNPC.chat();
            user.addRandomItem();

        }
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

        return false;
    }


}
