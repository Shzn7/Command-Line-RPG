import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLoad
{
    public SaveLoad() {} // empty constructor for completeness

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
        gc.put("username", playerUser.getUserName());
        for (Item item : playerUser.getInventory())
        {
            gc.put("inventory", item.toString());
        }
        gc.put("hitPoints", playerUser.getHP());
        gc.put("characterName", playerUser.getCharacterName());

        // Build final object to save
        JSONObject saveData = new JSONObject();
        saveData.put("gameConfig", gc);
        saveData.put("gameStep", step);
        saveData.put("playerUser", user);

        // if multiple saves are to be implemented, then
        // add this save data to a JSONArray here before
        // saving the whole array to the file below.

        // Generate file
        try (FileWriter saveFile = new FileWriter("saveGames.json"))
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
     *
     */
    public void loadGameStateFromFile(Game currentGame)
    {
        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader("saveGames.json"))
        {
            // Read the file
            Object obj = parser.parse(reader);
            JSONObject savedGame = (JSONObject) obj;

            // Parse the object
            // Game config info
            JSONObject gc = (JSONObject) savedGame.get("gameConfig");
            currentGame.setGamePath((String) gc.get("correctGamePath"));
            currentGame.setEncounterPath((String) gc.get("encounterPath"));
            currentGame.setEncounterType((String) gc.get("encounterType"));

            // Game step info
            JSONObject step = (JSONObject) savedGame.get("gameStep");
            // TODO


            // User state info
            JSONObject user = (JSONObject) savedGame.get("playerUser");
            // TODO



        }
        catch (FileNotFoundException e)
        {
            System.out.println("\nThere was no save file found.");
            System.out.println("Error message: " + e);
            System.out.println("Please save a game first.");
        }
        catch (IOException | ParseException e)
        {
            System.out.println("\nThere was an error saving the save game.");
            System.out.println("Error message: " + e);
            System.out.println("Please try again later.");
        }
    }
}
