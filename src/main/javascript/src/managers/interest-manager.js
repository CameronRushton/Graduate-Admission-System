import { AbstractManager } from "./abstract-manager";
import { HttpClient } from "aurelia-fetch-client";
import { inject } from "aurelia-framework";

@inject(HttpClient)
export class InterestManager extends AbstractManager{
    constructor(httpClient) {
		super(httpClient);
	}

	getInterests() {
     	var options = {
     		method: "GET",
     		headers: {
     			"Content-Type": "application/json"
     		},
     	};

     	return this.httpClient.fetch(``, options)
     		.then(this.handleError)
     		.then(this.json);
    }

    getInterest(id) {
    	var options = {
          	method: "GET",
          	headers: {
          		"Content-Type": "application/json"
          	},
        };

        return this.httpClient.fetch(`?id=${id}`, options)
          	.then(this.handleError)
          	.then(this.json);
    }

	getDepartmentInterests(department) {
        var options = {
        	method: "GET",
            headers: {
              	"Content-Type": "application/json"
            },
        };

        return this.httpClient.fetch(`department?department=${department}`, options)
            .then(this.handleError)
            .then(this.json);
    }

    getNewInterestFormInfo() {
        var options = {
        	method: "GET",
       		headers: {
          		"Content-Type": "application/json"
          	},
        };
        return this.httpClient.fetch(`create`, options)
        	.then(this.handleError);
    }

    addInterest(interest) {
    	var options = {
    		method: "POST",
    		headers: {
    			"Content-Type": "application/json"
    		},
    		body: JSON.stringify(interest)
    	};
    	return this.httpClient.fetch(`create`, options)
    		.then(this.handleError);
   	}

   	deleteInterest(id) {
    	var options = {
    		method: "DELETE"
    	};
    	return this.httpClient.fetch(`delete/id=${id}`, options)
    		.then(this.handleError);
   	}

   	getNewInterestFormInfo(id) {
		var options = {
        method: "GET",
        headers: {
        	"Content-Type": "application/json"
        	},
       	};
  		return this.httpClient.fetch(`update?id=${id}`, options)
     		.then(this.handleError);
    }

     updateInterest(interest) {
     	var options = {
        	method: "POST",
        	headers: {
        		"Content-Type": "application/json"
        	},
        	body: JSON.stringify(interest)
        };
        return this.httpClient.fetch(`update`, options)
        	.then(this.handleError);
       	}
}
