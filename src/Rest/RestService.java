package Rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/MyRestService")
@ApplicationPath("resources")
public class RestService extends Application {

    // http://localhost:8080/Spotify2Radio/resources/MyRestService/sayHello
    @GET
    @Path("/sayHello")
    public String getHelloMsg(){
        return "Hello World";
    }

    // http://localhost:8080/Spotify2Radio/resources/MyRestService/echo?message=Hej
    @GET
    @Path("/echo")
    public Response getEchoMsg(@QueryParam("message") String msg) {
        return Response.ok("Your message was: " + msg).build();
    }

    @GET
    @Path("/object")
    @Produces({MediaType.APPLICATION_XHTML_XML, MediaType.APPLICATION_JSON}) //klienten kan välja mellan att få objektet som XML eller JSON.
    public SimpleObject getObject(){
        return new SimpleObject(1, "Testing");
    }

    @GET
    @Path("/radiosongs")
    public Response getRadioSongs(@QueryParam("channel") String channel){
        RestClient restClient = new RestClient();
        return restClient.call(channel);
    }
}
