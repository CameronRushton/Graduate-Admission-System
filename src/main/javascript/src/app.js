import { inject, PLATFORM } from "aurelia-framework";
import { Router } from "aurelia-router"
import {activationStrategy} from 'aurelia-router';

@inject(Router)
export class App {

	constructor(router) {
		this.router = router;
	}

	navigateToRoute() {
		this.router.navigateToRoute('/')
	}

	configureRouter(config, router) {
		config.title = "Aurelia Initializr";
		config.options.pushState = true;
		config.options.root = "/";
		config.map([
			{
				route: '/',
				name: 'home',
				moduleId: PLATFORM.moduleName('pages/home/home'),
				title: "Home",
			},
			{
				route: '/login',
				name: 'login',
				moduleId: PLATFORM.moduleName('pages/login/login'),
				title: "Login",
			}
			{
            	route: '/interest/create',
            	name: 'add interest',
            	moduleId: PLATFORM.moduleName('pages/interest/edit'),
            	title: "Add Interest",
            }
            {
            route: '/interest/update',
            name: 'update interest',
            moduleId: PLATFORM.moduleName('pages/interest/edit'),
            title: "Update Interest",
            }
		]);
	}

}

