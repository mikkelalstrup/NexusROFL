package server.providers;

import server.models.Event;
import server.models.User;
import server.util.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */
public class EventProvider {

    /*
    PreparedStatetement for getting all events ordered by id from DB cafe_nexus
     */
    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> allEvents = new ArrayList<>();

        ResultSet resultSet = null;

        try {
            PreparedStatement getAllEventsStmt = null;

            getAllEventsStmt = DBManager.getConnection().
                    prepareStatement("SELECT * FROM events ORDER BY event_id");


            resultSet = getAllEventsStmt.executeQuery();

         /*
         Getting variables from Models_Event class
         and adding events to ArrayList
         */
            while (resultSet.next()) {
                Event event = new Event(
                        resultSet.getInt("event_id"),
                        resultSet.getString("title"),
                        resultSet.getTimestamp("created"),
                        new User(resultSet.getInt("owner_id")),
                        resultSet.getTimestamp("start"),
                        resultSet.getTimestamp("end"),
                        resultSet.getString("description"));

                allEvents.add(event);


            }

            resultSet.close();

            getAllEventsStmt.close();

        } catch (
                SQLException e)

        {
            e.printStackTrace();
        }

        return allEvents;

    }
}
