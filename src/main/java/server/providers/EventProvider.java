package server.providers;

import server.models.Event;
import server.util.DBManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Filip on 10-10-2017.
 */
public class EventProvider {


    public void createEvent(Event event) throws SQLException {

        PreparedStatement createEventStmt = DBManager.getConnection().prepareStatement("INSERT INTO events (title, description, start, events.end, owner_id) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        createEventStmt.setString(1, event.getTitle());
        createEventStmt.setString(2, event.getDescription());
        createEventStmt.setTimestamp(3, event.getStartDate());
        createEventStmt.setTimestamp(4, event.getEndDate());
        createEventStmt.setInt(5, event.getOwner().getId());

        createEventStmt.executeUpdate();

    }


}
