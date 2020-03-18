//altered from: https://stackoverflow.com/questions/41456403/aurelia-google-signin-button

import {inject, noView, bindable} from 'aurelia-framework';

const googleSigninClientID = '787575027862-t2vb0ae8ftk68nr9br9s4untp9e6t614.apps.googleusercontent.com';

function preparePlatform() {
  // https://developers.google.com/identity/sign-in/web/build-button

  // The name of the global function the platform API will call when
  // it's ready.
  const platformCallbackName = 'setGooglePlatformReady';

  // An "API ready" promise that will be resolved when the platform API
  // is ready.
  const ready = new Promise(
    resolve => window[platformCallbackName] = resolve);

  // Inject the client id meta tag
  const meta = document.createElement('meta');
  meta.name = 'google-signin-client_id';
  meta.content = googleSigninClientID;
  document.head.appendChild(meta);

  // Inject an async script element to load the google platform API.
  // Notice the callback name is passed as an argument on the query string.
  const script = document.createElement('script');
  script.src = `https://apis.google.com/js/platform.js?onload=${platformCallbackName}`;
  script.async = true;
  script.defer = true;
  document.head.appendChild(script);

  return ready;
}

const platformReady = preparePlatform();

@noView()
@inject(Element)
export class GoogleSigninButton {
  @bindable success = googleUser => { };
  @bindable error = error => { };
  @bindable scope = 'profile email';
  @bindable theme = 'dark';
  @bindable width = 240;
  @bindable height = 50;

  constructor(element) {
    this.element = element;
  }

  attached() {
    platformReady.then(this.renderButton);
  }

  renderButton = () => {
    gapi.signin2.render(this.element, {
      scope: this.scope,
      width: this.width,
      height: this.height,
      longtitle: true,
      theme: this.theme,
      onsuccess: googleUser => {
        this.success({ googleUser });
      },
      onfailure: error => {
        console.log(error);
      }
    });
  }
}
