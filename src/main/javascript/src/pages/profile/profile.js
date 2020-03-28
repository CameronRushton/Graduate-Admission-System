import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { Login } from 'pages/login/login';
import { UserManager } from 'managers/user-manager';

@inject(Router, Login, UserManager)
export class Profile {
    
    constructor(router, login, userManager) {
        this.router = router;
        this.authService = login;
        this.userManager = userManager;
        this.isDisabled = true;
    }

    attached() {
        this.currentUserId = this.authService.getCurrentUser().id;
        this.getUser();
    }

    getUser() {
        this.userManager.getUserById(this.currentUserId).then(result => {
            this.currentUser = result;
        }).catch(error => {
            console.log("Unable to retrieve user with ID " + this.currentUserId);
            // TODO: Alert popup
        })
    }

    saveUser() {
        this.saving = true;
        this.showSaveSuccess = false;
        this.showSaveError = false;
        let newUserInfo = {
            id: this.currentUserId,
            firstName: this.currentUser.firstName,
            lastName: this.currentUser.lastName,
            email: this.currentUser.email
        }
        this.userManager.updateUser(newUserInfo).then(result => {
            this.saving = false;
            this.showSaveSuccess = true;
            this.isDisabled = true;
        }).catch(error => {
            this.saving = false;
            this.showSaveError = true;
            if (error.status === 500) {
                this.errorMessage = "Unexpected error occurred."
            } else {
                this.errorMessage = "Failed to save user data."; //TODO: Replace with a message from the back end.
            }
        })
    }
}