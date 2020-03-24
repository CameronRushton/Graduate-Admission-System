import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { UserManager } from 'managers/user-manager';

@inject(Router, UserManager)
export class Profile {
    
    constructor(router, userManager) {
        this.router = router;
        this.userManager = userManager;
        this.scrollTop = 0;
    }

    attached() {
        this.getCurrentUser();
    }

    getCurrentUser() {
        this.userManager.getUserByEmail().then(result => {
            this.currentUser = result;
        }).catch(error => {
            // TODO: Display a meaningful error message to the user to indicate something went wrong.
            console.log('Error retrieving user info.');
            console.log(error);
        });
    }

}