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

    @GET
    public Response getAllEvents(){

        EventProvider eventProvider = new EventProvider();

        ArrayList<Event> allEvents = eventProvider.getAllEvents();

        return Response.status(200).type("text/plain").entity(new Gson().toJson(allEvents)).build();
    }


}
