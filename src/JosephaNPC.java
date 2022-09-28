public class JosephaNPC extends NPC{
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
