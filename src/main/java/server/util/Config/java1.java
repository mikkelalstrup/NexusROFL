package server.util.Config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class java1 {

    private static String DATABASE_HOST;
    private static String DATABASE_NAME;
    private static String DATABASE_PORT;
    private static String DATABASE_USER;
    private static String DATABASE_PASSWORD;

    public void initConfig() throws IOException {

        JsonObject json = new JsonObject();

        //Filen hentes i Inputstream
        //config.java åbnes for at kunne læses
        //Bufferedreader læser streamen igennem
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("/config.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        //Stringbuffer bruges til at samle hele filen i en streng
        StringBuffer stringBuffer = new StringBuffer();
        String str = "";

        //Filen læses en linje ad gangen og indlæses i stringbuffer
        while((str = reader.readLine()) != null){
            stringBuffer.append(str);
        }
        JsonParser parser = new JsonParser();

        json = (JsonObject) parser.parse(stringBuffer.toString());

        setDatabaseHost(json.get("DATABASE_HOST").toString().replace("\"",""));
        setDatabasePort(json.get("DATABASE_PORT").toString().replace("\"",""));
        setDatabaseName(json.get("DATABASE_NAME").toString().replace("\"",""));
        setDatabaseUser(json.get("DATABASE_USER").toString().replace("\"",""));
        setDatabasePassword(json.get("DATABASE_PASSWORD").toString().replace("\"",""));

    }

    public static String getDatabaseHost() {
        return DATABASE_HOST;
    }

    public static void setDatabaseHost(String databaseHost) {
        DATABASE_HOST = databaseHost;
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static void setDatabaseName(String databaseName) {
        DATABASE_NAME = databaseName;
    }

    public static String getDatabasePort() {
        return DATABASE_PORT;
    }

    public static void setDatabasePort(String databasePort) {
        DATABASE_PORT = databasePort;
    }

    public static String getDatabaseUser() {
        return DATABASE_USER;
    }

    public static void setDatabaseUser(String databaseUser) {
        DATABASE_USER = databaseUser;
    }

    public static String getDatabasePassword() {
        return DATABASE_PASSWORD;
    }

    public static void setDatabasePassword(String databasePassword) {
        DATABASE_PASSWORD = databasePassword;
    }
}
