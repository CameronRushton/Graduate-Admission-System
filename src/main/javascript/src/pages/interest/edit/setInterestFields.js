import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { InterestManager } from 'managers/interest-manager';
import { DepartmentManager } from 'managers/department-manager';

@inject(Router, InterestManager, DepartmentManager)
export class setInterestFormFields {

    constructor(router, interestManager, departmentManager) {
        this.router = router;
        this.interestManager = interestManager;
        this.scrollTop = 0;
		departmentManager.getDepartments().then(response => {
			this.departments = response;
		});



		//this.interestID
    }

    attached() {
    	var route_name = this.router.currentInstruction.config.name;

    	if(route_name == "add interest"){
    		console.log(this.interestManager.getNewInterestFormInfo());
    	}else if(route_name == "update interest"){
    		//console.log(this.interestManager.getUpdateInterestFormInfo());
    	}
    }

    handleScroll(event) {
        // We should be able to see the scroll position in the console when we uncomment the following line
        // console.log(this.scrollTop)
    }

    scrollToId(id) {
        document.getElementById(id).scrollIntoView({
            behavior: 'smooth'
        });
    }

    scrollToTopFn() {
        window.scrollTo({top: 0, behavior: 'smooth'});
        document.getElementById("top").scrollIntoView({
            behavior: 'smooth'
        });
    }

}


//$(setUpForm);
//
////populate the dropdown menu and set default choice
//function setUpForm() {
//	$("title")
//
//
//    $.get("/department/", function(data, status){
//        data.forEach(addOption);
//        $("#departments").val($("#department").val());
//    });
//
//    $("#submit").click(addDepartmentToForm);
//}
//
////add an option to the default menu
//function addOption(item, index) {
//    $("#departments").append("<option value='" + item + "'>" + item + "</option>");
//}
//
////when submit is clicked, include the selected option in the form
//function addDepartmentToForm(){
//    $("#department").val($("#departments option:selected").val());
//}
