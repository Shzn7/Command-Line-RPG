import java.util.*;

public class User extends Characters {
    private final List<Characters> defaultCharacters = Arrays.asList(
            new Characters("Ninja", 195, new ArrayList<>(Arrays.asList(new Nunchucks(), new Punch(), new Kick()))),
            new Characters("Wizard", 200, new ArrayList<>(Arrays.asList(new Wand(), new FireBall(), new Poison(), new Apple()))),
            new Characters("Pirate", 185, new ArrayList<>(Arrays.asList(new Sword(), new Pistol(), new SmallRock()))));
    public String userName;
    public GamemodesEnum gamemode;

    public User(String userName, String characterName, int HP, List<Item> inventory, GamemodesEnum gm) {
        super(characterName, HP, inventory);
        this.userName = userName;
        this.inventory = inventory;
        this.HP = HP;
        this.characterName = characterName;
        this.gamemode = gm;
    }

    // This constructor is used when selecting character at the beginning of the game
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
        String str = characterName + " | HP: " + HP + " | Attacks: ";

        for (int i = 0; i < inventory.size(); i++) {
            str += inventory.get(i);
            if (i != inventory.size()-1) {
                str += ", ";
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

        System.out.println("Congratulations, you have acquired a " +item+ "!");
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
