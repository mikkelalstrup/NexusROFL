package server.endpoints;

import com.google.gson.Gson;
import server.models.User;
import server.providers.UserProvider;
import server.util.Auth;
import server.util.DBManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/")
public class RootEndpoint {

    @GET
    public Response defaultGetMethod(){


        return Response.status(200).type("text/plain").entity("Welcome to our API").build();

    }
}
