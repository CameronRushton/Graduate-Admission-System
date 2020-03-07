import environment from "environment";

export class AbstractManager {

	constructor(httpClient) {
		// Further reading: https://livebook.manning.com/book/aurelia-in-action/chapter-8/40
		this.httpClient = httpClient;
		if (environment.host) {
			this.httpClient.configure(config => {
				config.withBaseUrl(environment.host)
			});
		}
	}

	toQueryString(obj) {
		var parts = [];
		for (var i in obj) {
			if (obj.hasOwnProperty(i)) {
				parts.push(encodeURIComponent(i) + "=" + encodeURIComponent(obj[i]));
			}
		}
		// This is the logic to act as a cache buster for IE
		if (!!navigator.userAgent.match(/Trident/g) || !!navigator.userAgent.match(/MSIE/g)) {
			parts.push(encodeURIComponent("date") + "=" + encodeURIComponent(new Date().getTime()))
		}
		if (parts.length > 0) {
			return `?${parts.join("&")}`;
		}
		return "";
	}

	handleError(response) {
		if (response.status >= 400 && response.status <= 599) {
			throw response;
		}
		return response;
	}

	json(response) {
		return response.json();
	}
}
