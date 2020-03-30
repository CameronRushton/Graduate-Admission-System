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

	updateApplication(application) {
		delete application["applicant"];
		delete application["showDetails"];

		var options = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(application)
		};
		return this.httpClient.fetch(`/application/update/status`, options)
			.then(this.handleError);
	}

	getApplicationsWithMatchingInterests(profId){
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			}
		};

		return this.httpClient.fetch(`/application/similar-interests?id=${profId}`, options)
			.then(this.handleError)
			.then(this.json);
	}

	getApplicationsWithMatchingStatus(status){
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			}
		};

		return this.httpClient.fetch(`/application/status?status=${status}`, options)
			.then(this.handleError)
			.then(this.json);
	}

	getApplications(){
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			}
		};

		return this.httpClient.fetch(`/application/`, options)
			.then(this.handleError)
			.then(this.json);
	}
}
