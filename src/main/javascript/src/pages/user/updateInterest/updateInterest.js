import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router";
import { UserManager } from 'managers/user-manager';
import { RoleManager } from 'managers/role-manager';
import { InterestManager } from 'managers/interest-manager';
import { ApplicationManager } from 'managers/Application-manager';

@inject(Router, UserManager, InterestManager)
export class updateUserInterest {

    constructor(router, userManager, interestManager) {
        this.router = router;
        this.scrollTop = 0;
        this.userManager = userManager;
        this.interestManager = interestManager;

    }

    attached() {
    	this.interestManager.getInterests().then(response => {
    			this.interests = response;
    		});
    	this.userManager.getAllUsers().then(response => {
				this.users = response;
			});
    }

    submitHandler(userId, userFirstName, userLastName, userEmail, userRole, userInterests, userApplications){
    		this.myUser = {
    			id: userId,
    			firstName: userFirstName,
    			lastName: userLastName,
    			email: userEmail,
    			role: userRole,
    			interest: userInterests,
    			applications: userApplications
    		}
		this.userManager.updateUser(this.myUser).then(()=>{this.router.navigateToRoute('update user')});
	}
}
