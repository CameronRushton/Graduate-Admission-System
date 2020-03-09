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
		departmentManager.getDepartments().then(response => {
			this.departments = response;
		});
    }

    attached() {}

	submitHandler(interestId, departmentChoice, interestKeyword){
		this.myInterest = {
			department: departmentChoice,
			keyword: interestKeyword
		}
		this.interestManager.addInterest(this.myInterest).then(()=>{this.router.navigateToRoute("view interests")});
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
