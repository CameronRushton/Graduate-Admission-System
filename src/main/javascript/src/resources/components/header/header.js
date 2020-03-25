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
		return true;
	}

	logout(){
		this.loginManager.logout();
	}
}
