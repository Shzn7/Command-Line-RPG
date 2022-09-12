/**
 * Testing Class to verify LoadGameConfiguration.java methods
 *
 * @author Shaazaan Majeed
 */

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.File;

public class LoadGameConfigurationTest {
    private final File testFile1 = new File("test/JsonTestFiles/testConfiguration1.json");
    private final File testFile2 = new File("test/JsonTestFiles/testConfiguration2.json");
    private final File testFile3 = new File("test/JsonTestFiles/testConfiguration3.json");
    private final File testFile4 = new File("test/JsonTestFiles/notARealFile.json");


    @Test(timeout = 1000)
    public void gameConfigTest1() {
        LoadGameConfiguration gameConfig = new LoadGameConfiguration().setGameConfigFromJsonFile(testFile1);

        assertEquals(gameConfig.getGamePath(), "NNWNENWNWSWNNWNESWWS");
        assertEquals(gameConfig.getEncounterPath(), "EWEESWEWSNESWEWNEESW");
        assertEquals(gameConfig.getEncounterType(), "RNRENRNENRREERNENNRE");
    }

    @Test(timeout = 1000)
    public void gameConfigTest2() {
        LoadGameConfiguration gameConfig = new LoadGameConfiguration().setGameConfigFromJsonFile(testFile2);

        assertEquals(gameConfig.getGamePath(), "NNNNNNSSSSEEEEWWWWNNNNEEEEWWWWNNNSSSSSS");
        assertEquals(gameConfig.getEncounterPath(), "NSWENWSWENSWNNSWEWSEWNSSEWESNNWSWEWSWEN");
        assertEquals(gameConfig.getEncounterType(), "RNRENRNENRREENNNERRNRENERNRENNEENNEEREE");
    }

    @Test(timeout = 1000)
    public void gameConfigTest3() {
        LoadGameConfiguration gameConfig = new LoadGameConfiguration().setGameConfigFromJsonFile(testFile3);

        assertEquals(gameConfig.getGamePath(), "NWWWENSSSNNNNNWWWNNWNNWE");
        assertEquals(gameConfig.getEncounterPath(), "");
        assertEquals(gameConfig.getEncounterType(), "");
    }

    @Test(timeout = 1000)
    public void gameConfigExceptionTest() {
        // Catch FileNotFoundException
        LoadGameConfiguration gameConfig = new LoadGameConfiguration().setGameConfigFromJsonFile(testFile4);

        assertNull(gameConfig.getGamePath());
        assertNull(gameConfig.getEncounterPath());
        assertNull(gameConfig.getEncounterType());
    }
}
