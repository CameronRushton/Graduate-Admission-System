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

    private Payload verifyToken(String idTokenString)
            throws Exception {
        final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.
                Builder(transport, jsonFactory)
                .setIssuers(Arrays.asList("https://accounts.google.com", "accounts.google.com"))
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken == null)
            throw new Exception("idToken is invalid");

        return idToken.getPayload();
    }

    @PostMapping("login")
    public ResponseEntity authenticateLogin(@RequestBody String loginDetails) {
        GoogleIdToken.Payload payload;
        try {
            payload = verifyToken(loginDetails);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Get profile information from payload
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        System.out.println("email: " + email);
        System.out.println("name: " + name);

        return ResponseEntity.ok("login success");
    }
}
