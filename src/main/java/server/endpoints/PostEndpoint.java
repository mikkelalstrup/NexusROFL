package server.endpoints;

import server.models.Post;
import server.providers.PostProvider;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.*;

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
}
