package server.providers;

import server.models.Post;
import server.util.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Filip on 10-10-2017.
 */
public class PostProvider {

    public int createPost(Post post) throws SQLException {

        //Creating prepared statement
        PreparedStatement createPostStatement =
                DBManager.getConnection().prepareStatement("INSERT INTO posts " + "(content, event_id, parent_id, user_id)" +
                        "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

        //Inserting values into the prepared statement
        createPostStatement.setString(1, post.getContent());

        if (post.getEvent().getId() == 0){
            createPostStatement.setNull(2, 1);
        } else {
            createPostStatement.setInt(2, post.getEvent().getId());
        }

        if (post.getParent().getId() == 0) {
            createPostStatement.setNull(3,1);
        } else {
            createPostStatement.setInt(3, post.getParent().getId());
        }

        createPostStatement.setInt(4, post.getOwner().getId());

        //Execute update
        int rowsUpdated = createPostStatement.executeUpdate();

        //Checking if a row has been updated
        if(rowsUpdated !=1) {
            throw new SQLException("Error with creating a post, no rows are affected");
        }

        //Collect generated Post id
        ResultSet generatedKeys = createPostStatement.getGeneratedKeys();

        //Checking if primary key has been created
        if(generatedKeys.next()) {
            post.setId(generatedKeys.getInt(1));
        } else {
            throw new SQLException("Error with creating post, could not retrieve ID");
        }

        //Closing query
        createPostStatement.close();

        //Returning Post id
        return post.getId();
    }
}
