import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class BuddyManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	addBuddy(buddy) {
		var options = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(buddy)
		};
		return this.httpClient.fetch(`create`, options)
		.then(this.handleError);
	}

	deleteBuddy(id) {
		var options = {
			method: "DELETE"
		};
		return this.httpClient.fetch(`delete?id=${id}`, options)
			.then(this.handleError);
	}

	getBuddies() {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`buddies`, options)
			.then(this.handleError)
			.then(this.json);
	}
}
