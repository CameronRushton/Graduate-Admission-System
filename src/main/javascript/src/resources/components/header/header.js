import { inject, bindable } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { LoginManager } from 'managers/login-manager';

@inject(Router, LoginManager)
export class Header {

    @bindable scroll_position;

    constructor(router, loginManager) {
        this.router = router;
        this.router.routes.forEach((route, index) => {
            if (route.name === "home") {
                route.char = "H";
            } else {
                route.char = route.title.charAt(0);
            }
        })
        this.showNavOptions = false;
        this.loginManager = loginManager;
    }

    // scroll_positionChanged(newValue, oldValue) {
    //     if (newValue > 90) {
    //         this.showNavOptions = false;
    //     }
    // }

    attached() {
        window.addEventListener("scroll", event => this.handleScrollEvent(event));
    }

    detached() {
        window.removeEventListener("scroll", event => this.handleScrollEvent(event))
    }

    handleScrollEvent(event) {
        this.scrollPosition = event.currentTarget.scrollY;
        if (this.scrollPosition > 90) {
            this.showNavOptions = false;
        }
    }

    // TODO: Move to dedicated auth service or login page
	isSignedIn(){
		//if the userID cookie is in the list of cookies, then we are signed in
		return document.cookie.indexOf('userID=') !== -1;
	}
    // TODO: Move to dedicated auth service or login page
	logout(){
		//delete the cookie by setting it to a date in the past
		document.cookie = "userID=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
		this.loginManager.logout();
	}
}
