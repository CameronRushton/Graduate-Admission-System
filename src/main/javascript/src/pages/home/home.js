import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { DepartmentManager } from 'managers/department-manager';

@inject(Router, DepartmentManager)
export class Home {
    
    constructor(router, departmentManager) {
        this.router = router;
        this.departmentManager = departmentManager;
        this.scrollTop = 0;

        this.departments = [];

    }

    attached() {
        this.getDepartments();
    }

    getDepartments() {
        this.departmentManager.getDepartments().then(result => {
            this.departments = result;
        }).catch(error => {
            // TODO: Display a meaningful error message to the user to indicate something went wrong.
            console.log('Error getting departments');
            console.log(error);
        });
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