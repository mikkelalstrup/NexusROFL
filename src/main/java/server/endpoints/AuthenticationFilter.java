package server.endpoints;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.annotation.Priority;
import javax.ws.rs.NameBinding;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    AuthEndpoint authEndpoint = new AuthEndpoint();
    private static final String AUTHENTICATION_SCHEME = "Bearer";
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authorizationHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            if(validateToken(token) == true){
                containerRequestContext.abortWith(Response.status(Response.Status.ACCEPTED).build());
            }
            else {
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean validateToken(String token) throws Exception {

        boolean isValidToken = true;
    try {


        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("ROFL").build();

        DecodedJWT jwt = verifier.verify(token);
    }
    catch (UnsupportedEncodingException e){
        isValidToken = false;
        //e.printStackTrace();
    } catch (JWTVerificationException e){
        //e.printStackTrace();
        isValidToken = false;
    }


        return isValidToken;
    }


}
