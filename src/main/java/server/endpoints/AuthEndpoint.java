package server.endpoints;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.gson.Gson;
import server.models.User;
import server.providers.UserProvider;
import server.util.Auth;

import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */

@Path("/auth")
public class AuthEndpoint {
    ArrayList<String> tokenArray = new ArrayList<String>();
   UserProvider userProvider = new UserProvider();
   User foundUser = new User();

   String checkHashed;

   @POST
   public Response AuthUser(String jsonUser) {
       User authUser = new Gson().fromJson(jsonUser, User.class);
       String token = null;

       try {
           foundUser = userProvider.getUserByEmail(authUser.getEmail());
       } catch (Exception e) {
           return Response.status(401).type("plain/text").entity("User not authorized").build();
       }
      checkHashed = Auth.hashPassword(authUser.getPassword(), foundUser.getSalt());

      if (checkHashed.equals(foundUser.getPassword())) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create().withIssuer("ROFL").sign(algorithm);
           // tokenArray.add(token);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (JWTCreationException e){
            e.printStackTrace();
        }
        
          return Response.status(200).type("plain/text").entity(token).build();
      } else {
          return Response.status(401).type("plain/text").entity("User not authorized").build();
      }
   }

   }
