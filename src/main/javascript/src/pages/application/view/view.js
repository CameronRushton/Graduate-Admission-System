import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router";
import { Login } from 'pages/login/login';
import { ApplicationManager } from 'managers/application-manager';
import { UserManager } from 'managers/user-manager';

@inject(Router, Login, ApplicationManager, UserManager)
export class ApplicationView {
    constructor(router, login, applicationManager, userManager) {
        this.router = router;
        this.scrollTop = 0;
        this.login = login;
        this.applicationManager = applicationManager;
        this.userManager = userManager;
	}

    attached() {
    	this.currentUser = this.login.getCurrentUser();
		this.userRoleName = this.currentUser.role.roleName;
		this.isStudent = this.userRoleName === "STUDENT";
		this.isProf = this.userRoleName === "PROFESSOR"
		this.isAdmin = this.userRoleName === "ADMIN"

		if (this.isStudent) {
			this.studentAttached();
		} else if(this.isProf) {
			this.professorAttached();
		} else if (this.isAdmin){
			this.adminAttached();
		}
    }

    studentAttached(){
		console.log("put your student attached function here!");
	}

	professorAttached(){
		this.applicationManager.getApplicationsForProf(this.currentUser.id).then(response => {
			this.requested = response;
			this.requested.forEach((application, index) => {
				application.showDetails = false;
				this.userManager.getUserByApplication(this.currentUser.id).then(response => {
					application.applicant = response[0]
			 	});
            });
		});
	}

	toggleDetails(id){
		this.requested.forEach((application, index) => {
			if(application.id === id){
				application.showDetails = !application.showDetails;
			}
		});
	}

	setApplicationStatus(id, status){
		this.requested.forEach((application, index) => {
			if(application.id === id){
				application.status = status;
			}
			this.applicationManager.updateApplication(application);
		});
	}

	adminAttached(){
		console.log("put your admin attached function here!");
	}


}
