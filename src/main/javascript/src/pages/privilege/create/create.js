import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { PrivilegeManager } from 'managers/privilege-manager';
import { OwnerManager } from 'managers/owner-manager';
import { TargetManager } from 'managers/target-manager';
import { OperationManager } from 'managers/operation-manager';

@inject(Router, PrivilegeManager, OwnerManager, TargetManager, OperationManager)
export class setPrivilegeFormFields {

    constructor(router, privilegeManager, ownerManager, targetManager, operationManager) {
        this.router = router;
        this.privilegeManager = privilegeManager;
        this.targetManager = targetManager;
        this.ownerManager = ownerManager;
        this.operationManager = operationManager;
        this.scrollTop = 0;
    }

    attached() {
    	this.ownerManager.getOwners().then(response => {
				this.owners = response;
			});
        this.operationManager.getOperations().then(response => {
				this.operations = response;
			});
		this.targetManager.getTargets().then(response => {
				this.targets = response;
			});
    }

	submitHandler(privilegeId, targetChoice, ownerChoice, operationChoice){
		this.myPrivilege = {
			target: targetChoice,
			owner: ownerChoice,
			operation: operationChoice
		}
		this.privilegeManager.addPrivilege(this.myPrivilege).then(()=>{this.router.navigateToRoute("view roles")});
	}
}
