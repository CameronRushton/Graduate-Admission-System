import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class TargetManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	getTargets() {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/targets/`, options)
			.then(this.handleError)
			.then(this.json);
	}
}
