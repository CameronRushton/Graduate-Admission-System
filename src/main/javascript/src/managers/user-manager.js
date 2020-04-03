import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class UserManager extends AbstractManager {
    constructor(httpClient) {
		super(httpClient);
	}

	getAllUsers() {
     	var options = {
     		method: "GET",
     		headers: {
     			"Content-Type": "application/json"
     		},
     	};

     	return this.httpClient.fetch(`/users`, options)
     		.then(this.handleError)
     		.then(this.json);
    }

    getUserById(id) {
    	var options = {
          	method: "GET",
          	headers: {
          		"Content-Type": "application/json"
          	},
        };

        return this.httpClient.fetch(`/users/${id}`, options)
          	.then(this.handleError)
          	.then(this.json);
    }

	getUsersByRole(roleName) {
        var options = {
        method: "GET",
            headers: {
            "Content-Type": "application/json"
            },
        };

        return this.httpClient.fetch(`/users/roles?roleName=${roleName}`, options)
            .then(this.handleError)
            .then(this.json);
    }

    getUsersByInterestId(interest_id) {
        var options = {
        method: "GET",
            headers: {
            "Content-Type": "application/json"
            },
        };

        return this.httpClient.fetch(`/users/interests/${interest_id}`, options)
            .then(this.handleError)
            .then(this.json);
    }

    getUserByEmail(email) {
        var options = {
        method: "GET",
            headers: {
            "Content-Type": "application/json"
            },
        };

        return this.httpClient.fetch(`/users/email?email=${email}`, options)
            .then(this.handleError)
            .then(this.json);
    }

    addUser(user) {
    	var options = {
    		method: "POST",
    		headers: {
    			"Content-Type": "application/json"
    		},
    		body: JSON.stringify(user)
    	};
    	return this.httpClient.fetch(`/users`, options)
            .then(this.handleError)
            .then(this.json);
       }

   	deleteUserById(id) {
    	var options = {
    		method: "DELETE"
    	};
    	return this.httpClient.fetch(`/users/${id}`, options)
    		.then(this.handleError);
   	}

    updateUser(user) {
        var options = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        };
        return this.httpClient.fetch(`/users`, options)
            .then(this.handleError)
    }

    updateUserRole(userId, newRole) {
		var options = {
			method: "PUT",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(newRole)
		};
		return this.httpClient.fetch(`/users/role?id=${userId}`, options)
			.then(this.handleError)
    }

    getStudentByApplication(application_id) {
		var options = {
		method: "GET",
			headers: {
			"Content-Type": "application/json"
			},
		};
		return this.httpClient.fetch(`/users/applicant?id=${application_id}`, options)
			.then(this.handleError)
			.then(this.json);
	}

	getApplicationsOfStudent(user_id) {
		var options = {
		method: "GET",
			headers: {
			"Content-Type": "application/json"
			},
		};

		return this.httpClient.fetch(`/application/applicant?id=${user_id}`, options)
			.then(this.handleError)
			.then(this.json);
	}

	 updateUserApplications(user) {
            var options = {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(user)
            };
            return this.httpClient.fetch(`/users/applications`, options)
                .then(this.handleError)
        }
}
