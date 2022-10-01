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

    public Characters() {}

    /**
     * Method for ensuring that when an item is used, the item's usage is decremented appropriately
     * or removed from the inventory if usage has run out.
     * @author Alex Basserabie
     */
    public void decrementInv(Item item) {
        int index = inventory.indexOf(item);
        if (inventory.get(index).getUsage() <= 1) {
            inventory.remove(item);
        } else {
            inventory.get(index).usage--;
        }

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
