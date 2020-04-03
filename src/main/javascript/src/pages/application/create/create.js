import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router";
import { ApplicationManager } from 'managers/application-manager';
import { UserManager } from 'managers/user-manager';
import { TermManager } from 'managers/term-manager';
import { DepartmentManager } from 'managers/department-manager';

@inject(Router, ApplicationManager, UserManager, TermManager, DepartmentManager)
export class ApplicationView {
    constructor(router, applicationManager, userManager, termManager, departmentManager) {
        this.router = router;
        this.scrollTop = 0;
        this.applicationManager = applicationManager;
        this.userManager = userManager;
        this.termManager = termManager;
        this.departmentManager = departmentManager;
	}

    attached() {


		this.termManager.getTerms().then(response => {
			this.activeTerms = [];

			response.forEach((term, index) => {
				if(term.active){
					this.activeTerms.push(term);
				}
			});
		});

		this.degrees = ["M.ASc", "M.Eng"];

		this.departmentManager.getDepartments().then(response => {
			this.departments = response;
		});

		this.userManager.getAllUsers().then(response => {
			this.professors = [];

			response.forEach((prof, index) => {
				if(prof.role.roleName === "PROFESSOR"){
					this.professors.push(prof);
				}
			});
		});
    }

    submitApplication(){
    	this.selectedSupervisors = [];
    	$(".supervisors input:checked").each((index, element) => {
				this.selectedSupervisors.push(element.value);
		});

    	console.log("term: " + this.selectedTerm);
    	console.log("degree: " + this.selectedDegree);
    	console.log("department: " + this.selectedDepartment);
    	console.log("gpa: " + this.gpa);
    	console.log("resumeName: " + this.resumeFileName);
    	console.log("supervisors: " + this.selectedSupervisors);
    }
}
