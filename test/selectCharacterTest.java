import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing Class to verify User.java constructors
 *
 * @author Deni Lanc
 */
public class selectCharacterTest {
    private final User user1 = new User(0);
    private final User user2 = new User(1);
    private final User user3 = new User(2);

    @Test(timeout = 1000)
    public void characterNameTest() {
        assertEquals(user1.getCharacterName(), "Ninja");
        assertEquals(user2.getCharacterName(), "Wizard");
        assertEquals(user3.getCharacterName(), "Pirate");
    }

    @Test(timeout = 1000)
    public void characterInventoryTest() {
        assertEquals(user1.inventory.get(0).getDamage(), new Nunchucks().getDamage());
        assertEquals(user1.inventory.get(1).getDamage(), new Punch().getDamage());
        assertEquals(user1.inventory.get(2).getDamage(), new Kick().getDamage());
        assertEquals(user2.inventory.get(0).getDamage(), new Wand().getDamage());
        assertEquals(user2.inventory.get(1).getDamage(), new FireBall().getDamage());
        assertEquals(user2.inventory.get(2).getDamage(), new Poison().getDamage());
        assertEquals(user3.inventory.get(0).getDamage(), new Sword().getDamage());
        assertEquals(user3.inventory.get(1).getDamage(), new Pistol().getDamage());
        assertEquals(user3.inventory.get(2).getDamage(), new SmallRock().getDamage());
    }

    @Test(timeout = 1000)
    public void characterStepTest() {
        assertEquals(user1.step, 0);
        assertEquals(user2.step, 0);
        assertEquals(user3.step, 0);
    }

    @Test(timeout = 1000, expected = ArrayIndexOutOfBoundsException.class)
    public void gameConfigExceptionTest() {
        // Catch User characterSelection out of bounds
        User user4 = new User(3);
    }
}
