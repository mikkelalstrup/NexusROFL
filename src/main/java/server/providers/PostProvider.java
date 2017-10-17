package server.providers;

import server.models.Event;
import server.models.Post;
import server.models.User;
import server.util.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */
public class PostProvider {

    public ArrayList<Post> getAllPosts() {
        ArrayList<Post> allPosts = new ArrayList<>();

        ResultSet resultSet = null;

        PreparedStatement getAllPostsStmt = null;
        try {
            getAllPostsStmt = DBManager.getConnection().prepareStatement("SELECT * FROM posts");

            resultSet = getAllPostsStmt.executeQuery();


             while (resultSet.next()){
                Post post = new Post(
                        resultSet.getInt("post_id"),
                        resultSet.getTimestamp("created"),
                        new User(resultSet.getInt("user_id")),
                        resultSet.getString("content"),
                        new Event(resultSet.getInt("event_id")),
                        new Post(resultSet.getInt("parent_id"))
                        );

                allPosts.add(post);

             }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPosts;

        }
}


