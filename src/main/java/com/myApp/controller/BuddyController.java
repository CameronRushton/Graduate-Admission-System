package com.myApp.controller;

import com.myApp.model.AddressBook;
import com.myApp.model.BuddyInfo;
import com.myApp.model.BuddyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@Controller
public class BuddyController {

    @Autowired
    BuddyInfoRepository repo;

    @GetMapping(value="/buddies")
    @ResponseBody
    public AddressBook buddies() {
        Iterable<BuddyInfo> buddies = repo.findAll();
        AddressBook book = new AddressBook();
        for (BuddyInfo bud : buddies) {
            book.addBuddy(bud);
        }
        return book;
    }

    @PostMapping("/create")
    @ResponseBody
    public BuddyInfo createBuddy(@RequestBody BuddyInfo buddy) {
        return repo.save(buddy);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String deleteBuddy(@RequestParam("id") Long id) {
        repo.deleteById(id);
        return "success";
    }

}
