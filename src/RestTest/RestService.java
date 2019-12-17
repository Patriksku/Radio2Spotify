package RestTest;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Path("/MyRestService")
@ApplicationPath("resources")
public class RestService extends Application {

    // http://localhost:8080/Radio2Spotify/resources/MyRestService/sayHello
    @GET
    @Path("/sayHello")
    public String getHelloMsg(){
        return "Hello World";
    }

    // http://localhost:8080/Radio2Spotify/resources/MyRestService/echo?message=Hej
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
    @Produces({MediaType.APPLICATION_XHTML_XML, MediaType.APPLICATION_JSON})
    public Response getRadioSongs(@QueryParam("channel") String channel) {
        RestClient restClient = new RestClient();

        return restClient.call(channel);
    }

    @GET
    @Path("/test")
    @Produces({MediaType.APPLICATION_XHTML_XML, MediaType.APPLICATION_JSON})
    public Document test() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder b = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document d = b.parse("http://api.sr.se/api/v2/playlists/rightnow?channelid=132");
        return d;
    }
}
