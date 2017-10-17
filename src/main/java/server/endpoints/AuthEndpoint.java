package server.endpoints;

import com.google.gson.Gson;
import server.models.User;
import server.providers.UserProvider;
import server.util.Auth;

import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */

@Path("/auth")
public class AuthEndpoint {

   UserProvider userProvider = new UserProvider();
   User foundUser = new User();

   String checkHashed;

   @POST
           public Response AuthUser(String jsonUser) {
       User authUser = new Gson().fromJson(jsonUser, User.class);


       try {
           foundUser = userProvider.getUserByEmail(authUser.getEmail());
       } catch (Exception e) {
           return Response.status(401).type("plain/text").entity("User not authorized").build();
       }
      checkHashed = Auth.hashPassword(authUser.getPassword(), foundUser.getSalt());

      if (checkHashed.equals(foundUser.getPassword())) {

          return Response.status(200).type("plain/text").entity("you are logged in").build();
      } else {
          return Response.status(401).type("plain/text").entity("User not authorized").build();
      }
   }

   }
