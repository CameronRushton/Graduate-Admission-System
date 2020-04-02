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
	}

    attached() {

    }
}
