import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class OwnerManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	getOwners() {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/owner`, options)
			.then(this.handleError)
			.then(this.json);
	}
}
