import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class LoginManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
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
    		.then(this.JSON);
   	}
}
