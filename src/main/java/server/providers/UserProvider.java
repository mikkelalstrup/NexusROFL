package server.providers;

import server.models.User;
import server.util.Auth;
import server.util.DBManager;

import javax.xml.ws.Response;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */
public class UserProvider {

    /**
     * Creates a new user in the database, requires a User object without a salt, and a plaintext password
     *
     * @param user The user that should be created in the database,
     *             should NOT contain a salt and use a PLAINTEXT password
     *
     * @return Returns the id of the user that has been generated
     */
    public int createUser(User user) throws SQLException {

        // Generate password salt
        user.setSalt(Auth.generateSalt(user.getPassword()));

        // Generate hashed password with salt
        user.setPassword(Auth.hashPassword(user.getPassword(), user.getSalt()));

        //Create prepared statement
            PreparedStatement createUserStatement =
                    DBManager.getConnection().prepareStatement("INSERT INTO users " +
                            "(salt, password, email, first_name, last_name, gender, description, major, semester) " +
                                    "VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        //Insert values into prepared statement
        createUserStatement.setString(1, user.getSalt());
        createUserStatement.setString(2, user.getPassword());
        createUserStatement.setString(3, user.getEmail());
        createUserStatement.setString(4, user.getFirstName());
        createUserStatement.setString(5, user.getLastName());
        createUserStatement.setString(6, String.valueOf(user.getGender()));
        createUserStatement.setString(7, user.getDescription());
        createUserStatement.setString(8, user.getMajor());
        createUserStatement.setInt(9, user.getSemester());

        //Execute update
        int rowsUpdated = createUserStatement.executeUpdate();

        // Check if 1 row have been updated
        if(rowsUpdated != 1) {
            throw new SQLException("Error creating user, no rows affected");
        }

        //Collect generated User id
        ResultSet generatedKeys = createUserStatement.getGeneratedKeys();

        // Check if a primary key (ID) has been created
        if(generatedKeys.next()) {
            user.setId(generatedKeys.getInt(1));
        } else {
            throw new SQLException("Error creating user, could not retrieve ID");
        }

        //Close query
        createUserStatement.close();

        //Return User id
        return user.getId();

    }

    public User getUserByEmail(String email){
        User user = null;

        ResultSet resultSet = null;

        try {
            PreparedStatement getUserByEmailStmt = DBManager.getConnection().prepareStatement
                    ("SELECT * FROM users WHERE email = ?");

            getUserByEmailStmt.setString(1, email);
            resultSet = getUserByEmailStmt.executeQuery();
            while(resultSet.next()){
                user = new User(
                        resultSet.getString("email"),
                        resultSet.getString("salt"),
                        resultSet.getString("password")
                );

            }

            if(user == null){
                throw new IllegalArgumentException();
            }

            resultSet.close();

          // getUserByEmailStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /*
    PreparedStatetement for getting all users ordered by id from DB cafe_nexus
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();

        ResultSet resultSet = null;

        try {
            PreparedStatement getAllUsersStmt = DBManager.getConnection().
                    prepareStatement("SELECT * FROM users ORDER BY user_id");


            resultSet = getAllUsersStmt.executeQuery();
    /*
    Getting variables from Models_User class
    and adding users to ArrayList
     */
            while(resultSet.next()){
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("description"),
                        resultSet.getString("gender").charAt(0),
                        resultSet.getString("major"),
                        resultSet.getInt("semester"));

                allUsers.add(user);
            }


            resultSet.close();

            getAllUsersStmt.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    public User getUser(int user_id){
        User user = null;

        ResultSet resultSet = null;

                try {
            PreparedStatement getUserStmt = DBManager.getConnection()
                    .prepareStatement("SELECT * FROM users WHERE user_id = ?");

            getUserStmt.setInt(1, user_id);

            resultSet = getUserStmt.executeQuery();

                while(resultSet.next()){
                    user = new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("salt"),
                            resultSet.getString("description"),
                            resultSet.getString("gender").charAt(0),
                            resultSet.getString("major"),
                            resultSet.getInt("semester")
                            /*
                            TO DO - her skal getPost, getEvent indsættes når de er færdige
                             */
                    );
                }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            return user;
    }

}
