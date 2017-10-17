/*


package server.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

public class Config {

    static {
        initConfig();
    }

    private static JsonObject jsonConfig;
    private static FileReader fileReader;

    // DB Connection settings
    private static String DATABASE_HOST;
    private static String DATABASE_PORT;
    private static String DATABASE_NAME;
    private static String DATABASE_USER;
    private static String DATABASE_PASSWORD;

    public static void initConfig() {
        // Read Config file

        try {
            FileReader fileReader = new FileReader("Config.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        jsonConfig = new Gson().fromJson(fileReader, JsonObject.class);


        DATABASE_HOST = jsonConfig.get("DATABASE_HOST").getAsString();
        DATABASE_PORT = jsonConfig.get("DATABASE_PORT").getAsString();
        DATABASE_NAME = jsonConfig.get("DATABASE_NAME").getAsString();
        DATABASE_USER = jsonConfig.get("DATABASE_USER").getAsString();
        DATABASE_PASSWORD = jsonConfig.get("DATABASE_PASSWORD").getAsString();
    }

    public static  String getDatabaseHost() {
        return DATABASE_HOST;
    }

    public static String getDatabasePort() {
        return DATABASE_PORT;
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static String getDatabaseUser() {
        return DATABASE_USER;
    }

    public static String getDatabasePassword() {
        return DATABASE_PASSWORD;
    }

}
 */