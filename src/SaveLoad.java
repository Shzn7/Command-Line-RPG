import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class to handle the saving and loading logic to and from
 * the JSON file 'saveGames.json'. Does not currently support
 * multiple save slots, but is designed such that implementing
 * them would not require much refactoring or any major changes.
 *
 * @author Brad Froud (u7285455)
 */
public class SaveLoad
{
    public SaveLoad() {} // empty constructor for completeness
    private static final String JSON_FILE = "src/Saves/saveGames.json";

    /**
     * A method to save the current state of the game and the user
     * to a file, so that it may be loaded into a running game later
     * on (will return the user to the point in the game the save was made)
     *
     * @param gameConfig the game configuration class used for the paths/rewards
     * @param gameStep the index/step of the game that the user is up to along the path
     * @param playerUser the user class of the current game to be saved to file
     *
     * @author Brad Froud (u7285455)
     */
    public void saveGameStateToFile(LoadGameConfiguration gameConfig, int gameStep, User playerUser)
    {
        // Build object related to game configuration
        JSONObject gc = new JSONObject();
        gc.put("correctGamePath", gameConfig.getGamePath());
        gc.put("encounterPath", gameConfig.getEncounterPath());
        gc.put("encounterType", gameConfig.getEncounterType());

        // Build object related to game progress
        JSONObject step = new JSONObject();
        step.put("index", gameStep);

        // Build objects related to user state
        JSONObject user = new JSONObject();
        user.put("username", playerUser.getUserName());
        JSONArray invList = new JSONArray();
        for (Item item : playerUser.getInventory())
        {
            JSONObject itemName = new JSONObject();
            itemName.put("itemName", item.toString());
            invList.add(itemName);
        }
        user.put("inventory", invList);
        user.put("hitPoints", playerUser.getHP());
        user.put("characterName", playerUser.getCharacterName());

        // Build final object to save
        JSONObject saveData = new JSONObject();
        saveData.put("gameConfig", gc);
        saveData.put("gameStep", step);
        saveData.put("playerUser", user);

        // if multiple saves are to be implemented, then
        // add this save data to a JSONArray here before
        // saving the whole array to the file below.

        // Generate file
        try (FileWriter saveFile = new FileWriter(JSON_FILE))
        {
            saveFile.write(saveData.toJSONString());
            saveFile.flush();
        }
        catch(IOException e)
        {
            System.out.println("\nThere was an error loading the save game.");
            System.out.println("Error message: " + e);
            // This could be changed to enable it automatically going back to character select.
            System.out.println("Please restart the game.");
        }
    }

    /**
     * A method to load a game state from a file, returning all
     * game state defining variables so that they may be used to
     * restore the current game's variables to what they were at
     * the point which the user saved their game/progress.
     * 
     * @return an ArrayList of gamePath, encounterPath, encounterType, gameStep, userName, inventory, HP, and characterName
     *
     * @author Brad Froud (u7285455)
     */
    public ArrayList<Object> loadGameStateFromFile()
    {
        ArrayList<Object> outputList = new ArrayList<Object>();

        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader(JSON_FILE))
        {
            // Read the file
            Object obj = parser.parse(reader);
            JSONObject savedGame = (JSONObject) obj;

            // Parse the object:

            // Game config info
            JSONObject gc = (JSONObject) savedGame.get("gameConfig");
            outputList.add((String) gc.get("correctGamePath"));
            outputList.add((String) gc.get("encounterPath"));
            outputList.add((String) gc.get("encounterType"));

            // Game step info
            JSONObject step = (JSONObject) savedGame.get("gameStep");
            outputList.add(Math.toIntExact((long) step.get("index")));

            // User state info
            JSONObject userJSON = (JSONObject) savedGame.get("playerUser");
            outputList.add((String) userJSON.get("username"));
            outputList.add(buildInventoryFromUserJSON(userJSON));
            outputList.add(Math.toIntExact((long) userJSON.get("hitPoints")));
            outputList.add((String) userJSON.get("characterName"));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("There was no save file found.");
            System.out.println("Please save a game first.");
        }
        catch (IOException | ParseException e)
        {
            System.out.println("\nThere was an error saving the save game.");
            System.out.println("Error message: " + e);
            System.out.println("Please try again later.");
        }

        return outputList;
    }

    /**
     * A helper method for the loadGameStateFromFile method used to generate
     * a list of items - an inventory - from an input JSON user which would
     * have been saved to file earlier and is now being loaded back into the
     * game.
     *
     * @param savedUser the JSONObject storing the user data which was saved
     * @return a list of items (an inventory)
     *
     * @author Brad Froud (u7285455)
     */
    public List<Item> buildInventoryFromUserJSON(JSONObject savedUser)
    {
        List<Item> outputList = new ArrayList<>();

        JSONArray invList = (JSONArray) savedUser.get("inventory");

        invList.forEach(item -> captureItem((JSONObject) item, outputList));

        return outputList;
    }

    /**
     * A helper method for buildInventoryFromUserJSON to interpret
     * the JSON container for a single item in string/name form and
     * save the appropriate item object to the output list from the
     * caller method.
     *
     * @param item a single inventory item in JSONObject format
     * @param outList list to add interpreted item to
     *
     * @author Brad Froud & Alex Basserabie
     */
    private void captureItem(JSONObject item, List<Item> outList)
    {
        String itemName = (String) item.get("itemName");

        List<Item> everything = new ArrayList<>(Arrays.asList(new EvilThoughts(), new FireBall(),
                new Headbutt(), new Kick(), new Lawsuit(), new Moan(), new Nunchucks(), new Pistol(), new Punch(), new SelfDrivingCar(),
                new SmallRock(), new StockMarket(), new Sword(), new TwitterAttack(), new Wand(), new ZombieBite()));

        boolean found = false;
        for (Item i : everything) {
            if (itemName.equals(i.toString())) outList.add(i);
            found = true;
        }

        if (!found) System.out.println("Error while loading: Invalid item detected in saved inventory.");
//
    }
}
