package server.endpoints;

import com.google.gson.Gson;
import server.models.Post;
import server.providers.PostProvider;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */
@Path("/posts")
public class PostEndpoint {

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response createPostMethod(
            @FormParam("owner") int owner,
            @FormParam("content") String content,
            @DefaultValue("0") @FormParam("event") int event,
            @DefaultValue("0") @FormParam("parent") int parent) {

        PostProvider postProvider = new PostProvider();

        Post post = new Post(owner, content, event, parent);

        try {
            post.setId(postProvider.createPost(post));

            return Response.status(201).type("text/plain").entity("Post created").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).type("text/plain").entity("Could not create post").build();
        }
    }

    /** This method returns one specific post chosen by id. The method creates an object of the PostProvider class and inserts
     * this object in an ArrayList and also the variables from the Post model-package.
     *
     *
     * @return It returns an response that converts the ArrayList onePost from Gson to Json.
     */
    @GET
    @Path("{id}")
    public Response getPost(@PathParam("id") int id) {
        PostProvider postProvider = new PostProvider(); //Creates an object

        Post post = postProvider.getPost(id);

        return Response.status(200).type("application/json").entity(new Gson().toJson(post)).build();
    }
}
