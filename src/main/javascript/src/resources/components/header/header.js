import { inject, bindable, Aurelia } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { LoginManager } from 'managers/login-manager';

@inject(Router, Aurelia, LoginManager)
export class Header {

    @bindable scroll_position;
    @bindable scroll_fn;

    constructor(router, aurelia, loginManager) {
        this.router = router;
        this.router.routes.forEach((route, index) => {
            if (route.name === "home") {
                route.char = "H";
            } else {
                route.char = route.title.charAt(0);
            }
        })
        this.showNavOptions = false;
        this.aurelia = aurelia;
        this.loginManager = loginManager;
    }

    scroll_positionChanged(newValue, oldValue) {
        if (newValue > 90) {
            this.showNavOptions = false;
        }
    }

	isSignedIn(){
		//if the userID cookie is in the list of cookies, then we are signed in
		return document.cookie.indexOf('userID=') !== -1;
	}

	logout(){
		//delete the cookie by setting it to a date in the past
		document.cookie = "userID=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
		this.loginManager.logout();
	}
}
