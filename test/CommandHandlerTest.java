

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Testing Class to verify CommandHandler.java methods
 *
 * @author Alex Basserabie
 */
public class CommandHandlerTest {

    @Test(timeout = 1000)
    public void northSouthEastWestPCTest() {
        assertEquals(new CommandHandler().processCommand("north"), CommandsEnum.NORTH);
        assertEquals(new CommandHandler().processCommand("south"), CommandsEnum.SOUTH);
        assertEquals(new CommandHandler().processCommand("east"), CommandsEnum.EAST);
        assertEquals(new CommandHandler().processCommand("west"), CommandsEnum.WEST);
    }

    @Test(timeout = 1000)
    public void northSouthEastWestVariationsPCTest() {
        assertEquals(new CommandHandler().processCommand("n"), CommandsEnum.NORTH);
        assertEquals(new CommandHandler().processCommand("sou"), CommandsEnum.SOUTH);
        assertEquals(new CommandHandler().processCommand("e"), CommandsEnum.EAST);
        assertEquals(new CommandHandler().processCommand("wes"), CommandsEnum.WEST);
    }


    @Test(timeout = 1000)
    public void otherEncounterPCTest() {
        assertEquals(new CommandHandler().processCommand("STATS"), CommandsEnum.STATS);
        assertEquals(new CommandHandler().processCommand("inv"), CommandsEnum.INV);
        assertEquals(new CommandHandler().processCommand("load"), CommandsEnum.LOAD);
        assertEquals(new CommandHandler().processCommand("save"), CommandsEnum.SAVE);
        assertEquals(new CommandHandler().processCommand("HELP"), CommandsEnum.HELP);
    }

    @Test(timeout = 1000)
    public void PCExceptionTest() {
        assertEquals(new CommandHandler().processCommand("hellllo"), CommandsEnum.NULL);
        assertEquals(new CommandHandler().processCommand("t"), CommandsEnum.NULL);
        assertEquals(new CommandHandler().processCommand("north1"), CommandsEnum.NULL);
    }

    @Test(timeout = 1000)
    public void PETest() {
        assertEquals(new CommandHandler().processEncounter('R'), CommandsEnum.REWARD);
        assertEquals(new CommandHandler().processEncounter('N'), CommandsEnum.NPC);
        assertEquals(new CommandHandler().processEncounter('E'), CommandsEnum.ENEMY);
    }

    @Test(timeout = 1000)
    public void PEExceptionTest() {
        assertEquals(new CommandHandler().processEncounter('Z'), CommandsEnum.NULL);
        assertEquals(new CommandHandler().processEncounter('.'), CommandsEnum.NULL);
    }
}
