package server.endpoints;


import com.google.gson.Gson;
import server.models.User;
import server.providers.UserProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import server.controllers.UserController;

import java.sql.SQLException;

/**
 * Created by Marc & Mikkel on 17-10-2017.
 *
 * This is our user endpoint, which handles the input given from the client regarding user-creation.
 */
@Path("/users")
public class UserEndpoint {
  
    /**
     * Instantiating userProvider and userController so they can be used throughout UserEndpoint.java
     */
    UserProvider userProvider = new UserProvider();
    UserController userController = new UserController();
  
    @GET
    public Response getAllUsers(){

        ArrayList<User> allUsers = userProvider.getAllUsers();

        return Response.status(200).type("application/json").entity(new Gson().toJson(allUsers)).build();

    }
  
  
    /**
     * This method lets the client create a new user. The parameters catches the specific input from the client.
     * The Endpoint creates a User object using the parameters stated below.
     * The User object is validated in UserController to makes that it is fitted for the database
     * The Endpoint throws 3 different Reponses, Statuscode: 201 (Succesful user creation), 400 (Wrong input by client), 501 (Database Error).
     *
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param gender
     * @param description
     * @param major
     * @param semester
     * @return
     */

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response createUserMethod(
            @FormParam("password") String password,
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("gender") String gender,
            @FormParam("description") String description,
            @FormParam("major") String major,
            @FormParam("semester") int semester){

            User createdUser = new User();

            try {

                /**
                 * validateGendeInput is called to make sure the String gender is no longer than 1 character.
                 * This way you can't register as male by inputting 'male' instead of 'm'
                 */
                userController.validateGenderInput(gender);
                createdUser = userController.validateUserCreation(password, firstName, lastName, email, gender.charAt(0), description, major, semester);
            }
            catch (IllegalArgumentException exception){
                System.out.println(exception.getMessage());
                return Response.status(400).build();
            }
            try {
                userProvider.createUser(createdUser);
            } catch (SQLException e) {
                return Response.status(501).type("text/plain").entity("Server couldn't store the validated user object (SQL Error)").build();
            }

        return Response.status(201).type("text/plain").entity("User Created").build();

    }


}
