import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { Login } from 'pages/login/login';

@inject(Router, Login)
export class Home {
    
    constructor(router, login) {
        this.router = router;
        this.authService = login;
    }

    attached() {
        this.currentUser = this.authService.getCurrentUser();
        console.log(this.currentUser);
        this.isAdmin = this.currentUser.role.roleName === "ADMIN";
        this.isProf = this.currentUser.role.roleName === "PROFESSOR";
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
}
