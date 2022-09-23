import java.util.List;

public class Characters {
    public int HP;
    List<Item> inventory;
    public String characterName;

    public Characters(String characterName, int HP, List<Item> inventory) {
        this.characterName = characterName;
        this.HP = HP;
        this.inventory = inventory;
    }

    public Characters() {

    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
