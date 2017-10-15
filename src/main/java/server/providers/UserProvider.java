package server.providers;

import server.models.User;
import server.util.Auth;

/**
 * Created by Filip on 10-10-2017.
 */
public class UserProvider {

    public void createUser(User user) {

        // Generate password salt
        user.setSalt(user.getPassword());

        // Generate hashed password with salt
        user.setPassword(Auth.hashPassword(user.getPassword(), user.getSalt()));

        //Create prepared statement

        //Insert values into prepared statement

        //Excecute update
        
        //Close query

    }

}
