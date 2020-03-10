import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class DepartmentManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	getDepartments() {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/departments`, options)
			.then(this.handleError)
			.then(this.json);
	}
}
