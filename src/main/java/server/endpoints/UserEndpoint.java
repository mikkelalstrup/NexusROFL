package server.endpoints;

import com.google.gson.Gson;
import server.models.User;
import server.providers.UserProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */


@Path("/users")
public class UserEndpoint {

    @GET
    public Response getAllUsers(){

        UserProvider userProvider = new UserProvider();

        ArrayList<User> allUsers = userProvider.getAllUsers();

        return Response.status(200).type("text/plain").entity(new Gson().toJson(allUsers)).build();
    }


}
