package server.endpoints;

import com.google.gson.Gson;
import server.controllers.ContentController;
import server.models.Event;
import server.providers.EventProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */

@Path("/events")
public class EventEndpoint {

    @GET
    @Path("{id}")
    public Response getEvent(@PathParam("id") int event_id){
        EventProvider eventProvider = new EventProvider();
        Event event = eventProvider.getEvent(event_id);
        
        return Response.status(200).type("application/json").entity(new Gson().toJson(event)).build();

    }
}
