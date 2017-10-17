package server.endpoints;

import server.util.DBManager;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.ws.Response;
import java.sql.SQLException;

/**
 * Created by Filip on 10-10-2017.
 */
@Path("/posts")
public class PostEndpoint {

    @POST
    public Response createPost() {


        }
    }

}
