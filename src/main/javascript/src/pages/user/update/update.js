import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router";
import { UserManager } from 'managers/user-manager';
import { RoleManager } from 'managers/role-manager';
import { InterestManager } from 'managers/interest-manager';

@inject(Router, UserManager, RoleManager, InterestManager)
export class updateUser {

    constructor(router, userManager, roleManager, interestManager) {
        this.router = router;
        this.scrollTop = 0;
        this.userManager = userManager;
        this.roleManager = roleManager;
        this.interestManager = interestManager;

    }

    attached() {
    	this.userManager.getAllUsers().then(response => {
				this.users = response;
			});
    	this.roleManager.getRoles().then(response => {
    			this.roles = response;
    		});
    	this.interestManager.getInterests().then(response => {
    			this.interests = response;
    		});
    }

	deleteUser(userId){
		this.userManager.deleteUserById(userId).then(()=>{
			this.userManager.getAllUsers().then(response => {
				this.users = response;
			});
		});
	}

	updateUser(user){
		this.userManager.updateUser(user).then(()=>{
			this.userManager.getAllUsers().then(response => {
				this.users = response;
			});
		});
	}
}
