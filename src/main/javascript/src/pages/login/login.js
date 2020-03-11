export class Login {
    
    constructor() {
        this.scrollTop = 0;
    }

    attached() {
    	$("head").append("<script src='https://apis.google.com/js/platform.js' async defer></script>");
		$("head").append("<meta name='google-signin-client_id' content='787575027862-t2vb0ae8ftk68nr9br9s4untp9e6t614.apps.googleusercontent.com'>");
    }

    scrollFn() {
        window.scrollTo({top: 0, behavior: 'smooth'});
        document.getElementById("top").scrollIntoView({ 
            behavior: 'smooth'
        });
    }

    onSignOut() {
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function () {
			console.log('User signed out.');
		});
	}

	onSignIn(googleUser) {
    	var profile = googleUser.getBasicProfile();
    	console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    	console.log('Name: ' + profile.getName());
    	console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
    }
}
