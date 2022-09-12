// JSON File Dependency Imports
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;

public class LoadGameConfiguration {
    private String correctGamePath;
    private String encounterPath;
    private String encounterType;

    public LoadGameConfiguration() {
    }

    public LoadGameConfiguration setGameConfigFromJsonFile(File file) {
        Gson gson = new Gson();
        JsonReader jsonReader = null;

        try {
            jsonReader = new JsonReader(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadGameConfiguration gameConfig = gson.fromJson(jsonReader, LoadGameConfiguration.class);
        return gameConfig;
    }

    public String getGamePath() {
        return correctGamePath;
    }

    public String getEncounterPath() {
        return encounterPath;
    }

    public String getEncounterType() {
        return encounterType;
    }
}
