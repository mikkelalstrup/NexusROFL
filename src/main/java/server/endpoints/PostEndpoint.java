package server.endpoints;

import com.google.gson.Gson;
import server.models.Post;
import server.providers.PostProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */
@Path("/posts")
public class PostEndpoint {

    /*
    This method returns all posts. To do so, the method creates an object of the PostProvider class
    and inserts this object in an arraylist along with the post from the models-package.

    Return response converts the arraylist getAllPosts from GSON to JSON.
     */
    @GET
    public Response getAllPosts(){

        PostProvider postProvider = new PostProvider();
        ArrayList<Post> allPosts = postProvider.getAllPosts();
        return Response.status(200).type("application/json").entity(new Gson().toJson(allPosts)).build();


    }
}
