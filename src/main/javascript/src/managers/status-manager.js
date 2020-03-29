import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class StatusManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	getStatuses() {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/statuses`, options)
			.then(this.handleError)
			.then(this.json);
	}
}
