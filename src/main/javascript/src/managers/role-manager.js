import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class RoleManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	getRoles() {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/role/`, options)
			.then(this.handleError)
			.then(this.json);
	}

	getRole(name) {
		var options = {
			method: "GET",
			headers: {
				"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/privilege/?name=${name}`, options)
			.then(this.handleError)
			.then(this.json);
	}

	addRole(role) {
		var options = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(role)
		};
		return this.httpClient.fetch(`/role/create`, options)
			.then(this.handleError);
    }

    deleteRole(name) {
		var options = {
			method: "DELETE"
		};
		return this.httpClient.fetch(`/role/${name}`, options)
			.then(this.handleError);
	}

	updateRole(role) {
		var options = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(role)
		};
		return this.httpClient.fetch(`/role/update`, options)
			.then(this.handleError);
	}

	addRolePrivilege(name, privilege) {
		var options = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(privilege)
		};
		return this.httpClient.fetch(`/role/add?name=${name}`, options)
			.then(this.handleError);
	}

	removeRolePrivilege(name, privilege) {
		var options = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(privilege)
		};
		return this.httpClient.fetch(`/role/remove?name=${name}`, options)
			.then(this.handleError);
	}
}
