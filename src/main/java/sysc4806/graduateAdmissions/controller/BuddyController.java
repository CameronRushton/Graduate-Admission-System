package sysc4806.graduateAdmissions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.AddressBook;
import sysc4806.graduateAdmissions.model.BuddyInfo;
import sysc4806.graduateAdmissions.repositories.BuddyInfoRepository;


@Controller
@CrossOrigin(maxAge = 3600)
public class BuddyController {

    @Autowired
    BuddyInfoRepository repo;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path="/buddies")
    public ResponseEntity buddies() {
        Iterable<BuddyInfo> buddies = repo.findAll();
        AddressBook book = new AddressBook();
        for (BuddyInfo bud : buddies) {
            book.addBuddy(bud);
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/create")
    public String createBuddyPage(Model model) {
        model.addAttribute("buddy", new BuddyInfo());
        return "create";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public String createBuddy(@ModelAttribute BuddyInfo buddy, Model model) {
        String name = buddy.getName();
        String address = buddy.getAddress();
        String number=buddy.getPhoneNumber();
        int age=buddy.getAge();
        model.addAttribute("name", name);
        repo.save(buddy);
        return "result";
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public String deleteBuddy(@RequestParam("id") Long id, Model model) {
        String buddyName = "Unknown";
        BuddyInfo bInfo = repo.findById(id).orElse(null);
        if (bInfo != null) {
            buddyName = bInfo.getName();
        }
        model.addAttribute("name", buddyName);
        repo.deleteById(id);
        return "deleteResult";
    }
}
