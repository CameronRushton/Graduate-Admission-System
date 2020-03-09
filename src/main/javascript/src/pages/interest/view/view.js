import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { InterestManager } from 'managers/interest-manager';
import { DepartmentManager } from 'managers/department-manager';

@inject(Router, InterestManager, DepartmentManager)
export class InterestView {

    constructor(router, interestManager, departmentManager) {
        this.router = router;
        this.interestManager = interestManager;
        this.scrollTop = 0;
		this.departmentFilter = "all";
        this.getInterestsByDepartment();

        departmentManager.getDepartments().then(response => {
			this.departments = response;
		});
    }

	getInterestsByDepartment(){
		let filter = this.departmentFilter

		if(filter === "all"){
			this.interestManager.getInterests().then(response => {
            	this.interests = response;
            });
		} else {
			this.interestManager.getDepartmentInterests(filter).then(response => {
				this.interests = response;
		   	});
		}
	}

    attached() {}

	deleteInterest(interestId){
		this.interestManager.deleteInterest(interestId).then(()=>{;
			this.getInterestsByDepartment();
		});
	}

	updateInterest(interestId, interestKeyword, departmentChoice){
		this.myInterest = {
			id: interestId,
        	department: departmentChoice,
        	keyword: interestKeyword
        }
		this.interestManager.updateInterest(this.myInterest);
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




//$(populate);
//
////add each interest to the page
//function populate(){
//    $.get("/interest/", function(data, status){
//        data.forEach(addDisplay);
//    });
//}
//
////add an interest with update and delete buttons
//function addDisplay(interest, index){
//    var interestContainer = document.createElement("DIV");
//    interestContainer.append(newP(interest.department));
//    interestContainer.append(newP(interest.keyword));
//    interestContainer.append(newButton("update", function(){
//            window.location.href = "/interest/update?id=" + interest.id;
//            }));
//    interestContainer.append(newButton("delete", function(){
//            $.ajax({
//                url: "/interest/delete/" + interest.id,
//                type: 'DELETE',
//                success: function(result) {
//                      window.location.reload();
//                }
//            });}));
//    $("#interests").append(interestContainer);
//}
//
////create a p tag with specified text
//function newP(text){
//    var p = document.createElement("P");
//    p.innerText = text;
//    return p;
//}
//
////create a button with specified text and onclick handler
//function newButton(text, handler){
//    var b = document.createElement("BUTTON");
//    var label = document.createTextNode(text);
//    b.appendChild(label);
//    b.addEventListener("click", handler);
//    return b;
//}
