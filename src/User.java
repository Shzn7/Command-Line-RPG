import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class User extends Characters {
    private final List<Characters> defaultCharacters = Arrays.asList(
            new Characters("Ninja", 195, new ArrayList<>(Arrays.asList(new Nunchucks(), new Punch(), new Kick()))),
            new Characters("Wizard", 200, new ArrayList<>(Arrays.asList(new Wand(), new FireBall(), new Poison(), new Apple()))),
            new Characters("Pirate", 185, new ArrayList<>(Arrays.asList(new Sword(), new Pistol(), new SmallRock()))));
    public String userName;
    public int step;

    public User(String userName, String characterName, int HP, List<Item> inventory) {
        super(characterName, HP, inventory);
        this.userName = userName;
        this.inventory = inventory;
        this.HP = HP;
        this.characterName = characterName;
    }

    // This constructor is used when selecting character at the beginning of the game
    public User (int characterSelection) {
        Characters temp = defaultCharacters.get(characterSelection);
        this.characterName = temp.characterName;
        this.HP = temp.HP;
        this.inventory = temp.inventory;
        this.userName = "";
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

    /**
     * Returns a string of the statistics of a given user for the purpose of informing the user of the stats of each character.
     * @author Alex Basserabie
     * @return String
     */
    public String introPrint() {
        String str = characterName + " | HP: " + HP + " | Attacks: ";
        for (Item i : inventory) {
            str += i + ", ";
        }
        return str;
    }

    /***
     * This method is used to add a random item to a character's inventory (a random reward) and tells the player what items have been obtained.
     * @author ZHUOJUN XIAO
     * @return null
     */
    public void addRandomItem(){
        Random random= new Random();
        int a= random.nextInt(5);

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
        else {
            item=new Headbutt();
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
        System.out.println("You have: ");
        for(var a: inventory){
            System.out.println(a);
        }
    }

    /**
     * Simple get method for the username
     * @return the current userName
     * @author Brad Froud (u7285455)
     */
    public String getUserName() {
        return this.userName;
    }

    // Sets the users username (different from character name)
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Example of making a User please remove when editing how we will be properly doing this in a game scenario
    public static void main(String[] args) {
        User bob = new User("bob", "Wizard", 100, null);
        System.out.println(bob.userName);
    }
}
