import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router";
import { Login } from 'pages/login/login';
import { ApplicationManager } from 'managers/application-manager';
import { UserManager } from 'managers/user-manager';
import { StatusManager } from 'managers/status-manager';

@inject(Router, Login, ApplicationManager, UserManager, StatusManager)
export class ApplicationView {
    constructor(router, login, applicationManager, userManager, statusManager) {
        this.router = router;
        this.scrollTop = 0;
        this.login = login;
        this.applicationManager = applicationManager;
        this.userManager = userManager;
        this.statusManager = statusManager;
        this.statusFilter = "all";
	}

    attached() {
    	this.currentUser = this.login.getCurrentUser();
		this.userRoleName = this.currentUser.role.roleName;
		this.isStudent = this.userRoleName === "STUDENT";
		this.isProf = this.userRoleName === "PROFESSOR"
		this.isAdmin = this.userRoleName === "ADMIN"

		if (this.isStudent) {
			this.getStudentApplications();
		} else if(this.isProf) {
			this.professorAttached();
		} else if (this.isAdmin){
			this.adminAttached();
		}
    }

    getStudentApplications(){
		this.userManager.getApplicationsOfStudent(this.currentUser.id).then(response => {
			this.applications = response;
		});
	}

	removeApplication(applicationId){
		this.currentUser.applications =
			this.currentUser.applications.filter((application) => { return application.id !== applicationId});
		this.userManager.updateUserApplications(this.currentUser).then(response => {
			this.applicationManager.removeApplication(applicationId).then(response => {
				this.getStudentApplications();
			});
		});
	}

	professorAttached(){
		this.getApplicationsByFilter();
		this.statusManager.getStatuses().then(response => {
			this.statuses = response;
		});
	}

	toggleDetails(id){
		let allApplications = this.requested.concat(this.matchingInterests);
		allApplications.forEach((application, index) => {
			if(application.detailToggleId === id){
				application.showDetails = !application.showDetails;
			}
		});
	}

	setApplicationStatus(id, status){
		if(this.isProf){
			let allApplications = this.requested.concat(this.matchingInterests);
			allApplications.forEach((application, index) => {
				if(application.id === id){
					application.status = status;
				}
				this.applicationManager.updateApplication(application);
			});
		} else if(this.isAdmin){
			let allApplications = this.requested;
			allApplications.forEach((application, index) => {
				if(application.id === id){
					let pos = this.requested.indexOf(application);
					application.status = status;
					if(!(this.statusFilter === "all")){
						this.requested.splice(pos, 1);
					}
				}
				this.applicationManager.updateApplication(application);
			});
		}
	}

	getApplicationsByFilter(){
		let filter = this.statusFilter
		this.requested = [];
		if(this.isProf){
			this.applicationManager.getApplicationsForProf(this.currentUser.id).then(response => {
				response.forEach((application, index) => {
					application.showDetails = false;
					application.detailToggleId = application.id + "requested"
					this.userManager.getUserByApplication(this.currentUser.id).then(response => {
						application.applicant = response[0];
						if(filter === "all" || application.status === filter){
							this.requested.push(application);
						}
					});
				});
			});
			this.applicationManager.getApplicationsWithMatchingInterests(this.currentUser.id).then(response => {
				this.matchingInterests = [];
				response.forEach((application, index) => {
					application.showDetails = false;
					application.detailToggleId = application.id + "similar-interest"
					this.userManager.getUserByApplication(this.currentUser.id).then(response => {
						application.applicant = response[0];
						if(filter === "all" || application.status === filter){
							this.matchingInterests.push(application);
						}
					});
				});
			});
		} else if(this.isAdmin){
			this.applicationManager.getApplications().then(response => {
				response.forEach((application, index) => {
					application.showDetails = false;
					application.detailToggleId = application.id + "requested"
					this.userManager.getUserByApplication(application.id).then(response => {
						application.applicant = response[0];
						if(filter === "all" || application.status === filter){
							this.requested.push(application);
						}
					});
				});
			});
		}
	}

	adminAttached(){
    	this.getApplicationsByFilter();
    	this.statusManager.getStatuses().then(response => {
			this.statuses = response;
		});
	}


}
