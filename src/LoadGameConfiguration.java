/**
 * LoadGameConfiguration.java class used to read game data from JSON file setting String variables
 * to represent data encodings
 * NOTE: additional checks on input validation not needed as input is controlled by Game Developers through
 * JSON file and not controlled by the user
 *
 * @author Shaazaan Majeed
 */

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;

public class LoadGameConfiguration {

    /**
     * Variables to store all game configuration data
     *
     * correctGamePath: N=North, E=East, S=South, W=West
     * encounterPath: N=North, E=East, S=South, W=West
     * encounterType: E=Enemy, N=NPC, R=Reward
     */
    private String correctGamePath;
    private String encounterPath;
    private String encounterType;

    public LoadGameConfiguration() {
    }

    /**
     * This method sets all game configuration variables using the JSON parser by Google (Gson) JSON File
     * specified by the JSON_FILE global final variable
     *
     * @author Shaazaan Majeed
     *
     * @param file, the JSON file to read in order to set variables
     * @return LoadGameConfiguration, setting all the private variables with their respective encodings as read by the JSON File
     */
    public LoadGameConfiguration setGameConfigFromJsonFile(File file) {
        Gson gson = new Gson();
        JsonReader jsonReader = null;
        try {
            jsonReader = new JsonReader(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jsonReader == null) return this;
        return gson.fromJson(jsonReader, LoadGameConfiguration.class);
    }


    /**
     * Getter method to retrieve the correctGamePath
     * @return correctGamePath String
     */
    public String getGamePath() {
        return correctGamePath;
    }

    /**
     * Getter method to retrieve the encounterPath
     * @return encounterPath String
     */
    public String getEncounterPath() {
        return encounterPath;
    }

    /**
     * Getter method to retrieve the encounterType
     * @return encounterType String
     */
    public String getEncounterType() {
        return encounterType;
    }
}
