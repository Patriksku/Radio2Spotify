package Rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

/**
 * Används för att ta emot information från APIs på internet, ex: SR, Spotify och Lyrics.
 */

public class RestClient {

    public Response call(String channelID) {
        Client client = ClientBuilder.newClient();

        String radioS = "http://api.sr.se/api/v2/playlists/rightnow?channelid=" + channelID;

        Response response = client.target(radioS).request().get();

        return response;
    }
}
