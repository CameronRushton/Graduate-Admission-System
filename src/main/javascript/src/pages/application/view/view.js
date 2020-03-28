import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router";
import { Login } from 'pages/login/login';

@inject(Router, Login)
export class ApplicationView {
    constructor(router, login) {
        this.router = router;
        this.scrollTop = 0;
        this.login = login;
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
		console.log("put your prof attached function here!");
	}

	adminAttached(){
		console.log("put your admin attached function here!");
	}
}
