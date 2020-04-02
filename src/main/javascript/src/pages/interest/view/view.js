import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { InterestManager } from 'managers/interest-manager';
import { DepartmentManager } from 'managers/department-manager';

@inject(Router, InterestManager, DepartmentManager)
export class InterestView {

    constructor(router, interestManager, departmentManager) {
        this.router = router;
        this.interestManager = interestManager;
        this.scrollTop = 0;
		this.departmentFilter = "all";
        this.departmentManager = departmentManager;
    }

	getInterestsByDepartment(){
		let filter = this.departmentFilter

		if(filter === "all"){
			this.interestManager.getInterests().then(response => {
            	this.interests = response;
            });
		} else {
			this.interestManager.getDepartmentInterests(filter).then(response => {
				this.interests = response;
		   	});
		}
	}

    activate() {
    	this.getInterestsByDepartment();

        this.departmentManager.getDepartments().then(response => {
			this.departments = response;
		});
    }

	deleteInterest(interestId){
		this.interestManager.deleteInterest(interestId).then(()=>{
			this.getInterestsByDepartment();
		});
	}

	updateInterest(interestId, interestKeyword, departmentChoice){
		this.myInterest = {
			id: interestId,
        	department: departmentChoice,
        	keyword: interestKeyword
        }
		this.interestManager.updateInterest(this.myInterest);
	}
}
