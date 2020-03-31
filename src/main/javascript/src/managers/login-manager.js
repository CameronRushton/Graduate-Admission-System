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
   		document.cookie = "sessionID="+this.getCookie("sessionID");

   		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
			"mode": 'cors',
			"credentials": "include"
		};
		return this.httpClient.fetch(`/logout`, options)
			.then(this.handleError)

		//these get deleted in the backend, so even if they were not deleted here they would be invalid
        document.cookie = "sessionID=5;expires= Thu, 21 Aug 2014 20:00:00 UTC";

		let auth2 = gapi.auth2.getAuthInstance();
		let parent = this;
		auth2.signOut().then(function () {
			parent.aurelia.setRoot(PLATFORM.moduleName('pages/login/login'));
		});
	}
}
