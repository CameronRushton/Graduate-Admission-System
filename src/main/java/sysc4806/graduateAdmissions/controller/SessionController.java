package sysc4806.graduateAdmissions.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sysc4806.graduateAdmissions.model.Session;
import sysc4806.graduateAdmissions.model.User;
import sysc4806.graduateAdmissions.repositories.SessionRepository;
import sysc4806.graduateAdmissions.repositories.UserRepository;
import sysc4806.graduateAdmissions.utilities.Utility;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Collections;

/**
 * This controller handles the creation and deletion of sessions in the system
 *
 * @author luke
 */
@RestController
@RequestMapping("/")
@Slf4j
public class SessionController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;
    private static final HttpTransport transport = new NetHttpTransport();
    private static final JsonFactory jsonFactory = new JacksonFactory();
    //this is assigned by the google sign in site
    private static final String CLIENT_ID = "787575027862-t2vb0ae8ftk68nr9br9s4untp9e6t614.apps.googleusercontent.com";

    /**
     * authenticates login tokens to set up user session
     *
     * @param loginDetails the token from google sign in to authenticate
     * @return OK if the token is valid, otherwise UNAUTHORIZED
     */
    @PostMapping("login")
    public ResponseEntity<String> authenticateLogin(@RequestBody String loginDetails) {
        val payload = verifyToken(loginDetails);
        if(payload != null) {
            try {
                val user = isValidUserEmail(payload.getEmail());
                val sessionId = createNewSession(user);
                log.info(payload.getEmail() + " signing in with session id" + sessionId);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Set-Cookie","sessionID="+sessionId+"; Max-Age=3600; Path=/; HttpOnly");
                return new ResponseEntity<>(user.getRole().getRoleName(), headers, HttpStatus.OK);
            } catch (InvalidLoginException e) {
                log.info(payload.getEmail() + " is not a valid email in the user database");
                return new ResponseEntity<>("Login failed: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Login failed: 3rd party token invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * performs token authentication with google and returns user info
     *
     * @param idTokenString the token to authenticate
     * @return returns user info if token is valid, otherwise null
     */
    private Payload verifyToken(String idTokenString) {
        val verifier = new GoogleIdTokenVerifier.
                Builder(transport, jsonFactory)
                .setIssuers(Arrays.asList("https://accounts.google.com", "accounts.google.com"))
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        try {
            val idToken = verifier.verify(idTokenString);
            assert(idToken != null);
            log.info("token " + idTokenString + " for " + idToken.getPayload().getEmail() + " validated");
            return idToken.getPayload();
        } catch(Exception | AssertionError  e) {
            log.info("token " + idTokenString + " is invalid");
            return null;
        }
    }

    /**
     * returns the User entity for a given email address
     *
     * @param email the email address to search for in the system
     * @return the USer object containing the passed email
     * @throws InvalidLoginException when there are 0 or multiple users with the email address.
     */
    private User isValidUserEmail(String email) throws InvalidLoginException {
        val usersMatchingEmail = userRepository.findByEmail(email);
        if(usersMatchingEmail.size() == 1){
            return usersMatchingEmail.get(0);
        } else if (usersMatchingEmail.size() > 1){
            throw new InvalidLoginException("there are multiple accounts with this email!");
        } else {
            throw new InvalidLoginException("no account with the specified email address is registered with the system");
        }
    }

    /**
     * creates a new session for the user signing in
     *
     * @param user the user to create the session for
     */
    private String createNewSession(User user) {
        /*users shouldn't have more than one session at a time, so first we remove
        any existing sessions for the user*/
        val currentUserSessions = sessionRepository.findByUser(user);
        for(Session session : currentUserSessions)
            sessionRepository.delete(session);

        /*the newly created session needs a new unique session id, which is used
        to create the session and then returned from the function*/
        String newSessionId;
        do {
            newSessionId = Utility.generateRandom64CharacterString();
        }while(!sessionRepository.findById(newSessionId).isEmpty());

        sessionRepository.save(new Session(newSessionId, user));
        return newSessionId;
    }
}

