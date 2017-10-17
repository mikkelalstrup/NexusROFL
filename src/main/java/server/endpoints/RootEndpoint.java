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
String test = null;
        try {
            test = DBManager.getConnection().getCatalog();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Response.status(200).type("text/plain").entity(test).build();
    }
}
