import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { BuddyManager } from 'managers/buddy-manager';

@inject(Router, BuddyManager)
export class Home {
    
    constructor(router, buddyManager) {
        this.router = router;
        this.buddyManager = buddyManager;
        this.scrollTop = 0;
        this.showSeeMore = false;
        setTimeout(() => { this.showSeeMore = true; }, 5000);

        this.buddies = [];
        this.name = "Name";
        this.address = "Address";
        this.number = "Number";
        this.age = "Age";

    }

    attached() {
        this.getBuddies();
    }

    getBuddies() {
        this.buddyManager.getBuddies().then(result => {
            this.buddies = result.buddies;
        }).catch(error => {
            console.log('Error getting buddies');
            console.log(error);
        });
    }

    addBuddy() {
        let buddy = {
            name: this.name,
            address: this.address,
            phoneNumber: this.number,
            age: this.age
        }
        this.buddyManager.addBuddy(buddy).then(result => {
            this.getBuddies();
        }).catch(error => {
            console.log('Error adding buddies');
            console.log(error);
        });
    }

    deleteBuddy(id) {
        this.buddyManager.deleteBuddy(id).then(result => {
            this.deletedBuddy = true;
            
            this.getBuddies();
        }).catch(error => {
            console.log('Error deleting buddy');
            console.log(error);
        });
    }

    selectTech(target) {
        this.technologies.forEach(tech => {tech.isActive = false});
        this.techDesc = target.desc;
        target.isActive = !target.isActive;
    }

    handleScroll(event) {
        // console.log(this.scrollTop)
    }

    scrollToId(id) {
        document.getElementById(id).scrollIntoView({ 
            behavior: 'smooth'
        });
    }

    scrollFn() {
        window.scrollTo({top: 0, behavior: 'smooth'});
        document.getElementById("top").scrollIntoView({ 
            behavior: 'smooth'
        });
    }

}