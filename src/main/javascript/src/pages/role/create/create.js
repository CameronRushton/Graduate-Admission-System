import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { RoleManager } from 'managers/role-manager';
import { PrivilegeManager } from 'managers/privilege-manager';

@inject(Router, RoleManager, PrivilegeManager)
export class setRoleFormFields {

    constructor(router, roleManager, privilegeManager) {
        this.router = router;
        this.roleManager = roleManager;
        this.privilegeManager = privilegeManager;
        this.scrollTop = 0;
        this.selectedPrivileges = [];
    }

    attached() {
    	this.privilegeManager.getPrivileges().then(response => {
    			this.privileges = response;
    		});
    }

	submitHandler(name, selectedPrivileges){
		this.myRole = {
			roleName: name,
			privileges: selectedPrivileges
		}
		this.roleManager.addRole(this.myRole).then(()=>{this.router.navigateToRoute("view roles")});
	}
}
