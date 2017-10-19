package server.endpoints;

import com.google.gson.Gson;
import server.models.Post;
import server.models.User;
import server.providers.UserProvider;
import server.util.Auth;
import server.util.DBManager;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/")
public class RootEndpoint {
    @GET
    public Response defaultGetMethod(){

        User user = new User("Password", "Filip", "Andersen", "fian16ab@student.cbs.dk", "MY password is password", 'M', "Ha(it)", 3);

        UserProvider userProvider = new UserProvider();

        try {
            userProvider.createUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Response.status(200).type("text/plain").entity("User created").build();

    }



}
