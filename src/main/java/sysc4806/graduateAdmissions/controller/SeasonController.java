package sysc4806.graduateAdmissions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sysc4806.graduateAdmissions.model.Season;

/**
 * A controller to retrieve the available Seasons in the system
 *
 * @author Eric
 *
 */
@RestController
public class SeasonController {

    /**
     * Provides all the available Seasons in the system as JSON
     *
     * @return JSON containing every Season name
     */
    @GetMapping("/seasons")
    public ResponseEntity getSeasons() {
        return ResponseEntity.status(HttpStatus.OK).body(Season.values());
    }
}
