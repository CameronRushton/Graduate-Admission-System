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

	getTerm(id) {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/terms/id?id=${id}`, options)
			.then(this.handleError)
			.then(this.json);
	}

	createTerm(termDTO) {
		var options = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(termDTO)
		};
		return this.httpClient.fetch(`/terms`, options)
			.then(this.handleError);
    }

    deleteTerm(id) {
		var options = {
			method: "DELETE"
		};
		return this.httpClient.fetch(`/terms/${id}`, options)
			.then(this.handleError);
	}

	updateTerm(termDTO) {
		var options = {
			method: "PUT",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(termDTO)
		};
		return this.httpClient.fetch(`/terms`, options)
			.then(this.handleError);
	}
}
