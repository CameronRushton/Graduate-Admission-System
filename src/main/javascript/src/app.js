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
				show: false,
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
				moduleId: PLATFORM.moduleName('pages/role/update/update'),
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
				icon: "glyphicon-home"
			},
			{
				show: true,
				route: '/application/view',
				name: 'view applications',
				moduleId: PLATFORM.moduleName('pages/application/view/view'),
				title: "Applications",
				icon: "glyphicon-file"
			},
			{
				show: true,
				route: '/profile',
				name: 'profile',
				moduleId: PLATFORM.moduleName('pages/profile/profile'),
				title: "Profile",
				icon: "glyphicon-user"
			},
			{
				show: false,
				route: '/login',
				name: 'login',
				moduleId: PLATFORM.moduleName('pages/login/login'),
				title: "Login",
			},
			{
				show: false,
				route: '/term/update',
				name: 'update term',
				moduleId: PLATFORM.moduleName('pages/term/update/update'),
				title: "Update Term"
			},
			{
				show: false,
				route: '/term/create',
				name: 'create term',
				moduleId: PLATFORM.moduleName('pages/term/create/create'),
				title: "Create Term"
			},
			{
				show: false,
				route: '/user/create',
				name: 'create user',
				moduleId: PLATFORM.moduleName('pages/user/create/create'),
				title: "Create User"
			},
			{
				show: false,
				route: '/user/update',
				name: 'update user',
				moduleId: PLATFORM.moduleName('pages/user/update/update'),
				title: "Update User"
			}
		]);
	}

}

