import java.util.ArrayList;
import java.util.List;

public class User extends Characters {
    public String userName;
    public int step;

    public User(String userName, String characterName, int HP, int step, ArrayList<Item> inventory) {
        this.userName = userName;
        this.step = step;
        this.inventory = inventory;
        this.HP = HP;
        this.characterName = characterName;
    }

    // This was on the whiteboard remove if unnecessary
    public void setAttack() {

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
