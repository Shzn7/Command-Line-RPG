import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CreateFunctionalInventoryTest {

    static User Alan = new User("bob", "Wizard", 100, new ArrayList<Item>(), GamemodesEnum.SURVIVAL );

    @Test(timeout = 1000)
    public void addIdentifiedItemTest() {
        Item apple = new Apple();
        Alan.addItem(apple);
        assertTrue(Alan.getInventory().contains(apple));
    }

    @Test(timeout = 1000)
    public void addRandomItemTest() {
        Alan.addRandomItem();
        assertTrue(Alan.getInventory()!=null);
    }
}
