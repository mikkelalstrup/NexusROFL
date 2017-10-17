package server.endpoints;

import com.google.gson.Gson;
import server.models.Event;
import server.providers.EventProvider;

import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */
@Path("/events")
public class EventEndpoint {


    /*
    This method returns all events. To do so, the method creates an object of the EventProvider-class
    and inserts this object in an arraylist along with the user from the models-package.

    Return response converts the arraylist allEvents from GSON to JSON
     */
    @GET
    public Response getAllEvents(){

        EventProvider eventProvider = new EventProvider();

        ArrayList<Event> allEvents = eventProvider.getAllEvents();

        return Response.status(200).type("text/plain").entity(new Gson().toJson(allEvents)).build();
    }


}
