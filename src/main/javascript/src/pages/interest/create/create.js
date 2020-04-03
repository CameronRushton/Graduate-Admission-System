import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { InterestManager } from 'managers/interest-manager';
import { DepartmentManager } from 'managers/department-manager';

@inject(Router, InterestManager, DepartmentManager)
export class setInterestFormFields {

    constructor(router, interestManager, departmentManager) {
        this.router = router;
        this.interestManager = interestManager;
        this.scrollTop = 0;
        this.departmentManager = departmentManager

    }

    activate() {
    	this.departmentManager.getDepartments().then(response => {
    			this.departments = response;
    		});
    }

	submitHandler(interestId, departmentChoice, interestKeyword){
		this.myInterest = {
			department: departmentChoice,
			keyword: interestKeyword
		}
		this.interestManager.addInterest(this.myInterest).then(()=>{
			this.departmentManager.getDepartments().then(response => {
				this.departments = response;
			});
		});
	}
}
