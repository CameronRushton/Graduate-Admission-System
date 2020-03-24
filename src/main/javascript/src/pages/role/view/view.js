import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { RoleManager } from 'managers/role-manager';
import { PrivilegeManager } from 'managers/privilege-manager';

@inject(Router, RoleManager, PrivilegeManager)
export class InterestView {

    constructor(router, roleManager, privilegeManager) {
        this.router = router;
        this.roleManager = roleManager;
        this.privilegeManager = privilegeManager;
        this.scrollTop = 0;
    }

    attached() {
		this.roleManager.getRoles().then(response => {
			this.roles = response;
		});
		this.privilegeManager.getPrivileges().then(response => {
			this.privileges = response;
		});
	}

	deleteRole(roleName){
		this.roleManager.deleteRole(roleName).then(()=>{
			this.roleManager.getRoles().then(response => {
				this.roles = response;
			});
		});
	}

	hasPrivilege(privilege){
		this.roleManager.deleteRole(roleName).then(()=>{
			this.roleManager.getRoles().then(response => {
				this.roles = response;
			});
		});
	}

	updateRole(roleName, privilegeChoices){
		this.myRole = {
			roleName: roleName,
			privileges: privilegeChoices
		}
		this.roleManager.updateRole(this.myRole);
	}
}
