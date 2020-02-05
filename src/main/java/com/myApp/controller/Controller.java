package com.myApp.controller;

import com.myApp.model.AddressBook;
import com.myApp.model.BuddyInfoRepository;
import com.myApp.model.BuddyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class Controller {

    private AddressBook addressBook;
    private BuddyInfoRepository repo;

    @Autowired
    public Controller(BuddyInfoRepository repository) {
        addressBook = new AddressBook();
        repo = repository;
    }

    public void addBuddy(BuddyInfo aBud) {
        addressBook.addBuddy(aBud);
        repo.save(aBud);
        // fetch all customers
        System.out.println("Buddies found with findAll():");
        System.out.println("-------------------------------");
        for (BuddyInfo customer : repo.findAll()) {
            System.out.println(customer.toString());
        }
        System.out.println("");
        System.out.println(addressBook.toString());
    }

//    @Bean
//    public CommandLineRunner demo() {
//        return (args) -> {
//            // save a few customers
//            repository.save(aBud);
//
//            // fetch all customers
//            System.out.println("Customers found with findAll():");
//            System.out.println("-------------------------------");
//            for (Customer customer : repository.findAll()) {
//                System.out.println(customer.toString());
//            }
//            System.out.println("");
//
//            // fetch an individual customer by ID
//            AddressBook customer = repository.findById(1L);
//            System.out.println("Customer found with findById(1L):");
//            System.out.println("--------------------------------");
//            System.out.println(customer.toString());
//            System.out.println("");
//
//            // fetch customers by last name
//            System.out.println("Customer found with findByLastName('Bauer'):");
//            System.out.println("--------------------------------------------");
//            repository.findByLastName("Bauer").forEach(bauer -> {
//                System.out.println(bauer.toString());
//            });
//            // for (Customer bauer : repository.findByLastName("Bauer")) {
//            //  System.out.printlnbauer.toString());
//            // }
//            System.out.println("");
//        };
//    }

    public void removeBuddy(int aBudIndex) {
        addressBook.removeBuddy(aBudIndex);
    }
}
