package sysc4806.graduateAdmissions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.AddressBook;
import sysc4806.graduateAdmissions.model.BuddyInfo;
import sysc4806.graduateAdmissions.model.BuddyInfoRepository;

@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("api/v0")
public class BuddyController {

    @Autowired
    BuddyInfoRepository repo;

    @RequestMapping(method = RequestMethod.GET, path="/buddies")
    @ResponseBody
    public ResponseEntity buddies() {
        Iterable<BuddyInfo> buddies = repo.findAll();
        AddressBook book = new AddressBook();
        for (BuddyInfo bud : buddies) {
            book.addBuddy(bud);
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping("/create")
    @ResponseBody
    public BuddyInfo createBuddy(@RequestBody BuddyInfo buddy) {
        return repo.save(buddy);
    }

    @DeleteMapping("/delete")
    public void deleteBuddy(@RequestParam("id") Long id) {
        repo.deleteById(id);
    }

}
