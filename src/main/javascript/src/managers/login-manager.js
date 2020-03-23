import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject, Aurelia } from "aurelia-framework";

@inject(HttpClient, Aurelia)
export class LoginManager extends AbstractManager{
    constructor(httpClient, aurelia) {
		super(httpClient);
		this.aurelia = aurelia;
	}

    login(token) {
    	var options = {
    		method: "POST",
    		headers: {
    			"Content-Type": "application/json"
    		},
    		body: token
    	};
    	return this.httpClient.fetch(`/login`, options)
    		.then(this.handleError)
   	}

   	logout() {
		let auth2 = gapi.auth2.getAuthInstance();
		let parent = this;
		auth2.signOut().then(function () {
			console.log('User signed out.');
			parent.aurelia.setRoot(PLATFORM.moduleName('pages/login/login'));
		});
	}
}
