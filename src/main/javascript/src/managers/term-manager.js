import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class TermManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	getTerms() {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/terms`, options)
			.then(this.handleError)
			.then(this.json);
	}
}
