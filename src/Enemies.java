import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Extends the character class, aallowing the developer to create enemies with certain characteristics.
 * @author Deni
 */

public class Enemies extends Characters{

    public Enemies(String characterName, int HP, List<Item> inventory) {
        super(characterName, HP, inventory);
    }

    @Override
    public String toString() {
        return characterName;
    }
}
