import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class OperationManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	getOperations() {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/operations`, options)
			.then(this.handleError)
			.then(this.json);
	}
}
