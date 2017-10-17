package server.endpoints;

import com.google.gson.Gson;
import server.providers.PostProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Filip on 10-10-2017.
 */
@Path("/posts")
public class PostEndpoint {

    @GET
    public Response getAllPosts(){

        PostProvider postProvider = new PostProvider();

        return Response.status(200).type("application/json").entity(new Gson().toJson(postProvider.getAllPosts())).build();


    }
}
