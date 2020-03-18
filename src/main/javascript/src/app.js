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
				show: false,
            	route: '/interest/view',
                name: 'view interests',
                moduleId: PLATFORM.moduleName('pages/interest/view/view'),
				title: "Interests",
				icon: "glyphicon-user"
            },
            {
           		show: false,
                route: '/interest/create',
                name: 'add interest',
                moduleId: PLATFORM.moduleName('pages/interest/create/create'),
                title: "Add Interest",
            },
            {
				show: false,
				route: '/interest/update',
				name: 'update interest',
				moduleId: PLATFORM.moduleName('pages/interest/create/create'),
				title: "Update Interest",
			},
			{
				show: true,
				route: '/',
				name: 'home',
				moduleId: PLATFORM.moduleName('pages/home/home'),
				title: "Home",
				icon: "glyphicon-home"
			},
			{
				show: true,
				route: '#',
				name: 'applications',
				moduleId: PLATFORM.moduleName('pages/home/home'), //TODO
				title: "Applications",
				icon: "glyphicon-file"
			},
			{
				show: false,
				route: '/login',
				name: 'login',
				moduleId: PLATFORM.moduleName('pages/login/login'),
				title: "Login",
			},
		]);
	}

}

