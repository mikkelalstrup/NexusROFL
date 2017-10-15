package server.endpoints;

import com.google.gson.Gson;
import server.models.User;
import server.providers.UserProvider;
import server.util.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class RootEndpoint {

    @GET
    public Response defaultGetMethod(){

        User testUser = new User("password", "Filip", "Andersen", "fian16ab@student.cbs.dk", "Im a test user", 'M', "Ha(it.)", 3);

        UserProvider userProvider = new UserProvider();

        userProvider.createUser(testUser);

        return Response.status(200).type("application/json").entity(new Gson().toJson(testUser)).build();
    }
}
