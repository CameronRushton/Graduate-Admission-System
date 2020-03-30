import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router";
import { UserManager } from 'managers/user-manager';
import { RoleManager } from 'managers/role-manager';
import { InterestManager } from 'managers/interest-manager';

@inject(Router, UserManager, RoleManager, InterestManager)
export class createUser {

    constructor(router, userManager, roleManager, interestManager) {
        this.router = router;
        this.scrollTop = 0;
        this.userManager = userManager;
        this.roleManager = roleManager;
        this.interestManager = interestManager;

    }

    attached() {
    	this.roleManager.getRoles().then(response => {
    			this.roles = response;
    		});
    	this.interestManager.getInterests().then(response => {
    			this.interests = response;
    		});
    }

	submitHandler(userFirstName, userLastName, userEmail, userRole, userInterests){
		this.myUser = {
			firstName: userFirstName,
			lastName: userLastName,
			email: userEmail,
			role: userRole,
			interest: userInterests,
			applications: []
		}
		this.userManager.addUser(this.myUser).then(()=>{this.router.navigateToRoute("update user")});
	}
}
