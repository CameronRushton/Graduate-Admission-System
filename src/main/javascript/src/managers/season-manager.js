import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class SeasonManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	getSeasons() {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/seasons`, options)
			.then(this.handleError)
			.then(this.json);
	}
}
