import { inject, bindable, Aurelia } from 'aurelia-framework';
import { Router } from "aurelia-router"

@inject(Router, Aurelia)
export class Header {

    @bindable scroll_position;
    @bindable scroll_fn;

    constructor(router, aurelia) {
        this.router = router;
        this.router.routes.forEach((route, index) => {
            if (route.name === "home") {
                route.char = "H";
            } else {
                route.char = route.title.charAt(0);
            }
        })
        this.showNavOptions = false;
        this.aurelia = aurelia
    }

    scroll_positionChanged(newValue, oldValue) {
        if (newValue > 90) {
            this.showNavOptions = false;
        }
    }

    onSignOut() {
		let auth2 = gapi.auth2.getAuthInstance();
		let parent = this;
		auth2.signOut().then(function () {
			console.log('User signed out.');
			parent.aurelia.setRoot(PLATFORM.moduleName('pages/login/login'));
		});
	}

	isSignedIn(){
		return true;
	}
}
