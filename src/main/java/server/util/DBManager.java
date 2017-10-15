package server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is responsible for establishing, delegating and closing the connection to the database.
 *
 * This class relies on enviroment variables to connect to the desired database, please set these up
 * before using the DBManager.
 */
public class DBManager {

    // Holds the connection to the database
    private static Connection connection;

    // Establishes the conncetion to the database
    static {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://"
                            + System.getenv("DATABASE_HOST") + ":"
                            + System.getenv("DATABASE_PORT") + "/"
                            + System.getenv("DATABASE_NAME") + "?useSSL=false&serverTimezone=GMT",
                    System.getenv("DATABASE_USER"),
                    System.getenv("DATABASE_PASSWORD"));
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Returns a Connection object, that can be used to make queries to the database
     */
    public static Connection getConnection() {
        return connection;
    }

}
