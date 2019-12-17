package RestAPI;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import XMLTools.EditXmlSR;

/**
 * This class contains all methods that interact with Sveriges Radio API.
 * Methods are based on HTTP-methods/verbs.
 *
 * The structure of the URI is: http://localhost:8080/Spotify2Radio/sveriges-radio/api/@Path
 * @author Patriksku
 */
@Path("/api")
@ApplicationPath("sveriges-radio")
public class SR extends Application {

    // http://localhost:8080/Spotify2Radio/sveriges-radio/api/sayHello
    @GET
    @Path("/sayHello")
    public String getHelloMsg(){
        return "Hello World";
    }


    // http://localhost:8080/Radio2Spotify/sveriges-radio/api/songs?channel=132
    /**
     * Returns previous and next song at specified Channel-ID (number).
     * @return XML or JSON-file of previous and current song.
     */
    @GET
    @Path("/songs")
    @Produces({MediaType.APPLICATION_XHTML_XML, MediaType.APPLICATION_JSON})
    public Document songs(@QueryParam("channel") String channel) {
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = docBuilder.parse("http://api.sr.se/api/v2/playlists/rightnow?channelid=" + channel);
            EditXmlSR edit = new EditXmlSR();
            Document songsDoc = edit.songs(document);
            return songsDoc;

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException sax) {
            sax.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
