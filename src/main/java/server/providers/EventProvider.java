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
    This method makes it possible to get all events from a specific user_id
     */
    public ArrayList<Event> getEventByOwnerId() {
        ArrayList<Event> events = new ArrayList<>();

        ResultSet resultSet = null;

        try {
            PreparedStatement getEventByOwnerIdStmt = DBManager.getConnection().
                    prepareStatement("SELECT * FROM events WHERE owner_id = ?");

            resultSet = getEventByOwnerIdStmt.executeQuery();

            while (resultSet.next()){
                Event event = new Event(
                        resultSet.getInt("event_id"),
                        resultSet.getString("titel"),
                        resultSet.getTimestamp("created"),
                        new User(resultSet.getInt("owner_id")),
                        resultSet.getTimestamp("startDate"),
                        resultSet.getTimestamp("endDate"),
                        resultSet.getString("description"));


                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;

    }
}
