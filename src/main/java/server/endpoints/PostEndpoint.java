package server.endpoints;

import server.models.Post;
import server.models.User;
import server.providers.PostProvider;
import server.util.DBManager;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
            @FormParam("event") int event,
            @FormParam("parent") int parent) {

        //Creating instance of PostProvider
        PostProvider postProvider = new PostProvider();

        //Creating Post object
        Post post = new Post(owner, content, event, parent);

        //Klad metode i Post pro
        try {
            postProvider.createPost(post);

            return Response.status(201).type("").entity("Post created").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(401).build();


        }


        }

    }
