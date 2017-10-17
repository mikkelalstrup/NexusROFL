package server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Filip on 10-10-2017.
 */


@Path("/users")
public class UserEndpoint {

    @GET
    public Response getAllUsers(){

        return Response.status(200).type("text/plain").entity("hello worlf").build();
    }


}
