import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Testing Class to verify CommandHandler.java methods
 *
 * @author Alex Basserabie
 */
public class battlesTest {

    @Test(timeout = 1000)
    public void EnemyVsPlayerSingleTest() {
        User u = new User(0);
        int userHealth = u.getHP();
        Interactions inter = new Interactions(u);
        inter.enemyVsPlayer();
        assertTrue(userHealth > u.getHP());
    }
    @Test(timeout = 1000)
    public void EnemyVsPlayerMultiTest() {
        User u = new User(0);
        int userHealth = u.getHP();
        Interactions inter = new Interactions(u);
        inter.enemyVsPlayer();
        inter.enemyVsPlayer();
        inter.enemyVsPlayer();
        assertTrue(userHealth > u.getHP());
    }

    @Test(timeout = 1000)
    public void ParseInputValidTest() {
        String input = "1";
        User u = new User(0);
        Interactions inter = new Interactions(u);
        assertTrue(inter.parseInput(input));
    }

    @Test(timeout = 1000)
    public void ParseInputInvalidStringTest() {
        String input = "bas";
        User u = new User(0);
        Interactions inter = new Interactions(u);
        assertFalse(inter.parseInput(input));
    }

    @Test(timeout = 1000)
    public void ParseInputInvalidIndexTest() {
        String input = "200";
        User u = new User(0);
        Interactions inter = new Interactions(u);
        assertFalse(inter.parseInput(input));
    }
}
