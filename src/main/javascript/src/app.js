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
				show: true,
            	route: '/interest/view',
                name: 'view interests',
                moduleId: PLATFORM.moduleName('pages/interest/view/view'),
                title: "Interests",
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
				route: '/role/view',
				name: 'view roles',
				moduleId: PLATFORM.moduleName('pages/role/view/view'),
				title: "Roles",
			},
			{
				show: false,
				route: '/role/create',
				name: 'add role',
				moduleId: PLATFORM.moduleName('pages/role/create/create'),
				title: "Add Role",
			},
			{
				show: false,
				route: '/role/update',
				name: 'update role',
				moduleId: PLATFORM.moduleName('pages/role/create/create'),
				title: "Update Role",
			},
			{
				show: false,
				route: '/privilege/create',
				name: 'add privilege',
				moduleId: PLATFORM.moduleName('pages/privilege/create/create'),
				title: "Add Privilege",
			},
			{
				show: true,
				route: '/',
				name: 'home',
				moduleId: PLATFORM.moduleName('pages/home/home'),
				title: "Home",
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

