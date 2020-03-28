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

        let mockUser = {
            id: 123,
            firstName: "Test",
            lastName: "User",
            email: "GAS.student4806@gmail.com",
            role: {
                roleName: "Student",
                privileges: []
            },
            interests: [
                {
                    id: 56,
                    department: "SYSC",
                    keyword: "Some sysc keywords"
                },
                {
                    id: 57,
                    department: "AERO",
                    keyword: "Some aero keywords"
                }
            ],
            applications: [
                {
                    id: 5,
                    applicant: 123, // At the time of writing, the fetch type is eager which means we have a circular dependency and we can't infinitely fetch users and applications
                    term: {
                        id: 6,
                        active: true,
                        deadline: "2020-07-01",
                        season: "SUMMER",
                        year: 2020
                    },
                    department: "SYSC",
                    degree: "Master of Engineering",
                    professors: [45, 65],
                    status: "SUBMITTED",
                    gpa: 12.0,
                    resumeFileName: "myResumeFileName"
                },
                {
                    id: 6,
                    applicant: 123, // At the time of writing, the fetch type is eager which means we have a circular dependency and we can't infinitely fetch users and applications
                    term: {
                        id: 6,
                        active: true,
                        deadline: "2020-07-01",
                        season: "SUMMER",
                        year: 2020
                    },
                    department: "AERO",
                    degree: "Master of Engineering",
                    professors: [45, 67],
                    status: "SUBMITTED",
                    gpa: 12.0,
                    resumeFileName: "myResumeFileName"
                }
            ]
        }
        return new Promise.resolve((resolve) => {
            resolve({ 
                json: () => mockUser 
            })
        })

        // return this.httpClient.fetch(`/users/email?email=${email}`, options)
        //     .then(this.handleError)
        //     .then(this.json);
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
            .then(this.json);
    }
}
