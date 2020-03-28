import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class ApplicationManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	getApplicationsForProf(profId) {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			}
		};

		return this.httpClient.fetch(`/application/requested-prof?id=${profId}`, options)
			.then(this.handleError)
			.then(this.json);
	}
}
