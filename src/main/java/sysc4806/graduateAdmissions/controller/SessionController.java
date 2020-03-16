package sysc4806.graduateAdmissions.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

/**
 * This controller handles the creation and deletion of sessions in the system
 *
 * @author luke
 */
@RestController
@RequestMapping("/")
public class SessionController {
    private static final HttpTransport transport = new NetHttpTransport();
    private static final JsonFactory jsonFactory = new JacksonFactory();
    //this is assigned by the google sign in site
    private static final String CLIENT_ID = "787575027862-t2vb0ae8ftk68nr9br9s4untp9e6t614.apps.googleusercontent.com";

    /**
     * performs token authenticate with google and returns user info
     *
     * @param idTokenString the token to authenticate
     * @return returns user info if token is valid, otherwise null
     */
    private Payload verifyToken(String idTokenString) {
        final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.
                Builder(transport, jsonFactory)
                .setIssuers(Arrays.asList("https://accounts.google.com", "accounts.google.com"))
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(idTokenString);
        }catch(Exception e){
            return null;
        }

        if (idToken == null)
            return null;

        return idToken.getPayload();
    }

    /**
     * authenticates login tokens to set up user session
     *
     * @param loginDetails the token from google sign in to authenticate
     * @return OK if the token is valid, otherwise UNAUTHORIZED
     */
    @PostMapping("login")
    public ResponseEntity authenticateLogin(@RequestBody String loginDetails) {
        GoogleIdToken.Payload payload = verifyToken(loginDetails);
        if(payload != null)
            //TODO: check if the email in the token matches a user and create a session for that user
            return ResponseEntity.ok("login success");
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
