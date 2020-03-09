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
        this.getInterestsByDepartment();

        departmentManager.getDepartments().then(response => {
			this.departments = response;
		});
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

    attached() {}

	deleteInterest(interestId){
		this.interestManager.deleteInterest(interestId).then(()=>{;
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

    handleScroll(event) {
        // We should be able to see the scroll position in the console when we uncomment the following line
        // console.log(this.scrollTop)
    }

    scrollToId(id) {
        document.getElementById(id).scrollIntoView({
            behavior: 'smooth'
        });
    }

    scrollToTopFn() {
        window.scrollTo({top: 0, behavior: 'smooth'});
        document.getElementById("top").scrollIntoView({
            behavior: 'smooth'
        });
    }
}
