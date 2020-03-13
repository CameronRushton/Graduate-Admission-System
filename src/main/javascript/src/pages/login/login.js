import {Aurelia, inject} from 'aurelia-framework';
import { Router } from "aurelia-router"
import { LoginManager } from 'managers/login-manager';

@inject(Router, Aurelia, LoginManager)
export class Login {
    
    constructor(router, aurelia, loginManager) {
        this.scrollTop = 0;
        this.router = router;
        this.aurelia = aurelia;
        this.loginManager = loginManager;
    }

    attached() {
    	$("head").append("<script src='https://apis.google.com/js/platform.js' async defer></script>");
		$("head").append("<meta name='google-signin-client_id' content='787575027862-t2vb0ae8ftk68nr9br9s4untp9e6t614.apps.googleusercontent.com'>");
    }

    scrollFn() {
        window.scrollTo({top: 0, behavior: 'smooth'});
        document.getElementById("top").scrollIntoView({ 
            behavior: 'smooth'
        });
    }

	onSignIn(googleUser) {
    	let profile = googleUser.getBasicProfile();
    	console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    	console.log('Name: ' + profile.getName());
    	console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.

    	//send login token to server to start session
		let id_token = googleUser.getAuthResponse().id_token;
		let parent = this;
		this.loginManager.login(id_token).then(response => {
			//load rest of the app
            parent.aurelia.setRoot('app');
		});
    }
}
