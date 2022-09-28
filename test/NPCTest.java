import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing Class to verify CommandHandler.java methods
 *
 * @author Alex Basserabie
 */
public class NPCTest {

    @Test(timeout = 1000)
    public void LuChenNpcCheckAnswerTest() {

        LuChenNPC npc = new LuChenNPC("ALAN");
        npc.checkAnswer("A");
        assertFalse(npc.getAddItem());
        npc.checkAnswer("B");
        assertTrue(npc.getAddItem());

    }

    @Test(timeout = 1000)
    public void WuKongNpcCheckAnswerTest() {

       WuKongNPC wuKong = new WuKongNPC("daSheng");
        wuKong.checkAnswer("A");
        assertFalse(wuKong.getAddItem());
        wuKong.checkAnswer("B");
        assertFalse(wuKong.getAddItem());
        wuKong.checkAnswer("C");
        assertFalse(wuKong.getAddItem());
        wuKong.checkAnswer("D");
        assertTrue(wuKong.getAddItem());



    }



}
