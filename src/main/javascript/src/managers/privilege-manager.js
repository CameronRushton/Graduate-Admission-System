import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class PrivilegeManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	getPrivileges() {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/privileges/`, options)
			.then(this.handleError)
			.then(this.json);
	}

	getPrivilege(id) {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/privileges/?id=${id}`, options)
			.then(this.handleError)
			.then(this.json);
	}

	getOwnerPrivileges(owner) {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/privileges/owner?owner=${owner}`, options)
			.then(this.handleError)
			.then(this.json);
	}

	getTargetPrivileges(target) {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/privileges/target?target=${target}`, options)
			.then(this.handleError)
			.then(this.json);
	}

	getOperationPrivileges(operation) {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/privileges/operation?operation=${operation}`, options)
			.then(this.handleError)
			.then(this.json);
	}

	addPrivilege(privilege) {
		var options = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(privilege)
		};
		return this.httpClient.fetch(`/privileges/`, options)
			.then(this.handleError);
	}

	deletePrivilege(id) {
		var options = {
			method: "DELETE"
		};
		return this.httpClient.fetch(`/privilege/${id}`, options)
			.then(this.handleError);
	}

	updatePrivilege(privilege) {
		var options = {
			method: "PUT",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(privilege)
		};
		return this.httpClient.fetch(`/privileges/`, options)
			.then(this.handleError);
	}

}
