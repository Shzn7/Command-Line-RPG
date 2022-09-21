import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User extends Characters {
    public String userName;
    public int step;

    public User(String userName, String characterName, int HP, int step, List<Item> inventory) {
        this.userName = userName;
        this.step = step;
        this.inventory = inventory;
        this.HP = HP;
        this.characterName = characterName;
    }

    // This was on the whiteboard remove if unnecessary
    public void setAttack() {

    }

    /**
     * This method is used to add an already identified item to the character's inventory and tells the player what items have been obtained.
     * @author ZHUOJUN XIAO
     * @param item
     * @return null
     */

    public void addItem(Item item){
        inventory.add(item);
        System.out.println("Congratulations, you have acquired" + item +"!");
    }

    /***
     * This method is used to add a random item to a character's inventory (a random reward) and tells the player what items have been obtained.
     * @author ZHUOJUN XIAO
     * @return null
     */
    public void addRandomItem(){
        Random random= new Random();
        int a= random.nextInt(4);

        Item item = null;
        if(a==0){
            item=new Apple();
        }
        else if (a==1){
            item=new HealthPotion();
        }
        else if(a==2){
            item=new SmallRock();
        }
        else if(a==3){
            item=new Sword();
        }

        inventory.add(item);

        System.out.println("Congratulations, you have acquired " +item+ "!");
    }

    /***
     * Display the user's inventory's Items information.
     * @author ZHUOJUN XIAO
     * @return null
     */
    public void displayInventory(){
        for(var a: inventory){
            System.out.println("Item:\n"+a);
        }
    }





    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Example of making a User please remove when editing how we will be properly doing this in a game scenario
    public static void main(String[] args) {
        User bob = new User("bob", "Wizard", 100, 0, null);
        System.out.println(bob.userName);
    }
}
