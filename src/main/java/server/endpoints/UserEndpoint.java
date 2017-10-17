package server.endpoints;

import server.controllers.UserController;
import server.models.User;
import server.providers.UserProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.sql.SQLException;

/**
 * Created by Filip on 10-10-2017.
 */

@Path("/users/")
public class UserEndpoint {


    UserController userController = new UserController();

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response createUserMethod(
            @FormParam("password") String password,
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("gender") char gender,
            @FormParam("description") String description,
            @FormParam("major") String major,
            @FormParam("semester") int semester){

            userController.validateUserCreation(password, firstName,lastName,email,gender,description,major,semester);

        return Response.status(201).type("text/plain").entity("User created").build();

    }


}
