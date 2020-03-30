import {Aurelia, inject} from 'aurelia-framework';
import { Router } from "aurelia-router"
import { LoginManager } from 'managers/login-manager';
import {PLATFORM} from 'aurelia-pal';
import 'pages/login/login.scss';

@inject(Router, Aurelia, LoginManager)
export class Login {
    
    constructor(router, aurelia, loginManager) {
        this.scrollTop = 0;
        this.router = router;
        this.aurelia = aurelia;
        this.loginManager = loginManager;
    }

    attached() {
    	//this script is required to use google sign in
    	$("head").append("<script src='https://apis.google.com/js/platform.js' async defer></script>");
    	//the client id here is assigned by the google sign in site
		$("head").append("<meta name='google-signin-client_id' content='787575027862-t2vb0ae8ftk68nr9br9s4untp9e6t614.apps.googleusercontent.com'>");
    }

	onSignIn(googleUser) {
    	//send login token to server to authenticate
		let id_token = googleUser.getAuthResponse().id_token;
		let parent = this;
		this.loginManager.login(id_token).then(response => {//the handler for login success
			//create session cookie and load rest of app
			response.json().then(content => {
				this.user = content.user;
				console.log(this.user.role.roleName);
				parent.aurelia.setRoot('app');
				document.cookie = "userID="+content.user.id+";path=/;"; //if no one id actually using this, we can remove it
				document.cookie = "sessionID="+content.id+";path=/;";
			});
		}).catch(err => {//the handler for login rejection
			err.text().then(
				msg => {
					alert(msg);
					this.loginManager.logout();
				})
		 });
	}
	
	getCurrentUser() {
		return this.user;
	}
}
