import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { Login } from 'pages/login/login';
import { UserManager } from 'managers/user-manager';

@inject(Router, Login, UserManager)
export class Home {
    
    constructor(router, login, userManager) {
        this.router = router;
        this.authService = login;
        this.userManager = userManager;
    }

    attached() {
        this.currentUserId = this.authService.getCurrentUser().id;
        this.getUser();
    }

    getUser() {
        this.userManager.getUserById(this.currentUserId).then(result => {
            this.currentUser = result;
            console.log(this.currentUser);
            this.isAdmin = this.currentUser.role.roleName === "ADMIN";
            this.isProf = this.currentUser.role.roleName === "PROFESSOR";
        }).catch(error => {
            console.log("Unable to retrieve user with ID " + this.currentUserId);
            // TODO: Alert popup
        })
    }

    getInterests() {
        this.departmentManager.getDepartments().then(result => {
            this.departments = result;
        }).catch(error => {
            // TODO: Display a meaningful error message to the user to indicate something went wrong.
            console.log('Error getting departments');
            console.log(error);
        });
    }

    navigateToRoute(route) {
        this.router.navigateToRoute(route);
    }
}
