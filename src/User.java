import java.util.*;

public class User extends Characters {
    private final List<Characters> defaultCharacters = Arrays.asList(
            new Characters("Ninja", 135, new ArrayList<>(Arrays.asList(new Nunchucks(), new Punch(), new Kick()))),
            new Characters("Wizard", 115, new ArrayList<>(Arrays.asList(new Wand(), new FireBall(), new Poison(), new Apple()))),
            new Characters("Pirate", 120, new ArrayList<>(Arrays.asList(new Sword(), new Pistol(), new SmallRock()))));
    public String userName;
    public GamemodesEnum gamemode;

    //Constructor is used a couple of times to create the OP Mode character and for testing.
    public User(String userName, String characterName, int HP, List<Item> inventory, GamemodesEnum gm) {
        super(characterName, HP, inventory);
        this.userName = userName;
        this.inventory = inventory;
        this.HP = HP;
        this.characterName = characterName;
        this.gamemode = gm;
    }

    /**
     * This constructor is used when selecting character at the beginning of the game when survival mode selected.
     * Returns a new User based off the selected default character.
     * @author Deni Lanc
     */
    public User (int characterSelection) {
        Characters temp = defaultCharacters.get(characterSelection);
        this.characterName = temp.characterName;
        this.HP = temp.HP;
        this.inventory = temp.inventory;
        this.userName = "";
        this.gamemode = GamemodesEnum.SURVIVAL;
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
        String str = characterName + " | HP: " + HP;

        List<Item> notWeap = new ArrayList<>();
        List<Item> weap = new ArrayList<>();

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).isWeapon()) {
                weap.add(inventory.get(i));
            } else {
                notWeap.add(inventory.get(i));
            }
        }

        if (weap.size() != 0) {
            str  += " | Attacks: ";
            for (int i = 0; i < weap.size(); i++) {
                str += weap.get(i);
                if (i != weap.size()-1) {
                    str += ", ";
                }
            }
        }

        if (notWeap.size() != 0) {
            str  += " | Items: ";
            for (int i = 0; i < notWeap.size(); i++) {
                str += notWeap.get(i);
                if (i != notWeap.size()-1) {
                    str += ", ";
                }
            }
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
        int a= random.nextInt(Game.EVERYTHING.size());
        Item item = Game.EVERYTHING.get(a);
        inventory.add(item);
        String str = "";
        if (item.isWeapon()) str += " Attack";

        System.out.println("Congratulations, you have acquired a " + item + str + "!");
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

    public GamemodesEnum getGamemode() {
        return gamemode;
    }

    /**
     * Rewritten equals function, as identical inventories didn't equal one another by default.
     * @return boolean
     * @author Alex Basserabie
     */

    @Override
    public boolean equals(Object ob) {
        User u = (User) ob;
        if (!Objects.equals(this.characterName, u.characterName)) return false;
        if (!Objects.equals(this.getHP(), u.getHP())) return false;
        if (!Objects.equals(this.inventory.size(), u.inventory.size())) return false;
        for (int i = 0; i < this.inventory.size(); i++){
            System.out.println(this.inventory.get(i) + " , " + u.inventory.get(i));
            if (!Objects.equals(this.inventory.get(i), u.inventory.get(i))) {
                return false;
            }
        }
        return Objects.equals(this.gamemode, u.gamemode);
    }


}
