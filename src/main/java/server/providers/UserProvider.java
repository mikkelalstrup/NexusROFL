package server.providers;

import server.models.User;
import server.util.Auth;
import server.util.DBManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Filip on 10-10-2017.
 */
public class UserProvider {

    public void createUser(User user) {

        // Generate password salt
        user.setSalt(Auth.generateSalt(user.getPassword()));

        // Generate hashed password with salt
        user.setPassword(Auth.hashPassword(user.getPassword(), user.getSalt()));

        //Create prepared statement
        try {
            PreparedStatement createUser =
                    DBManager.getConnection().prepareStatement
                            ("INSERT INTO users (salt, password, email, first_name, last_name, gender, description, major, semester) " +
                                    "VALUES (?,?,?,?,?,?,?,?,?)");

            //Insert values into prepared statement
            createUser.setString(1, user.getSalt());
            createUser.setString(2, user.getPassword());
            createUser.setString(3, user.getEmail());
            createUser.setString(4, user.getFirstName());
            createUser.setString(5, user.getLastName());
            createUser.setString(6, String.valueOf(user.getGender()));
            createUser.setString(7, user.getDescription());
            createUser.setString(8, user.getMajor());
            createUser.setInt(9, user.getSemester());

            //Excecute update
            int rowsUpdated = createUser.executeUpdate();

            if(rowsUpdated != 1) {
                throw new SQLException("Error creating user");
            }

            //Close query
            createUser.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }







    }

}
