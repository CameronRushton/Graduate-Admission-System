import environment from './environment';
import {PLATFORM} from 'aurelia-pal';
import '@babel/polyfill';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap';

// remove out if you don't want a Promise polyfill (remove also from webpack.config.js)
//Bluebird.config({ warnings: { wForgottenReturn: false } });

export function configure(aurelia) {
  aurelia.use
    .standardConfiguration()
    .feature(PLATFORM.moduleName('resources/index'));

  // Uncomment the line below to enable animation.
  // aurelia.use.plugin(PLATFORM.moduleName('aurelia-animator-css'));
  // if the css animator is enabled, add swap-order="after" to all router-view elements

  // Anyone wanting to use HTMLImports to load views, will need to install the following plugin.
  // aurelia.use.plugin(PLATFORM.moduleName('aurelia-html-import-template-loader'));

  aurelia.use.developmentLogging(environment.debug ? 'debug' : 'warn');

  if (environment.testing) {
    aurelia.use.plugin(PLATFORM.moduleName('aurelia-testing'));
  }

  return aurelia.start()
      .then(a => {
        if (userIsLoggedIn()) {
          a.setRoot(PLATFORM.moduleName('app'));
        } else {
          a.setRoot(PLATFORM.moduleName('pages/login/login'));
        }
      });
}

function  userIsLoggedIn(){
	//check if i have a session
	return false;
}
