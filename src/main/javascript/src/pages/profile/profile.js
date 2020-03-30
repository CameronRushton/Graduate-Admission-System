import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { Login } from 'pages/login/login';
import { UserManager } from 'managers/user-manager';
import { DepartmentManager } from 'managers/department-manager';
import { InterestManager } from 'managers/interest-manager';
import { observable } from 'aurelia-framework';


@inject(Router, Login, UserManager, DepartmentManager, InterestManager)
export class Profile {

    @observable
    selectedDepartment;
    
    constructor(router, login, userManager, departmentManager, interestManager) {
        this.router = router;
        this.authService = login;
        this.userManager = userManager;
        this.departmentManager = departmentManager;
        this.interestManager = interestManager;
        this.filteredInterests = [];
        this.isDisabled = true;
    }

    attached() {
        this.currentUserId = this.authService.getCurrentUser().id;
        this.isAdmin = this.authService.getCurrentUser().role.roleName === "ADMIN";
        this.getUser();
        Promise.all([
            this.departmentManager.getDepartments(),
            this.interestManager.getInterests()
        ]).then(results => {
            this.departments = results[0];
            this.interests = results[1];
            this.selectedDepartment = this.departments[0];
        })
    }

    getUser() {
        this.userManager.getUserById(this.currentUserId).then(result => {
            this.currentUser = result;
        }).catch(error => {
            console.log("Unable to retrieve user with ID " + this.currentUserId);
            // TODO: Alert popup
        })
    }

    selectedDepartmentChanged(newDepartment) {
        this.filteredInterests = this.interests.filter(interest => {
            return interest.department == newDepartment;
        })
    }

    saveUser(handleResultFn) {
        this.saving = true;
        this.showSaveSuccess = false;
        this.showSaveError = false;
        let newUserInfo = {
            id: this.currentUserId,
            firstName: this.currentUser.firstName,
            lastName: this.currentUser.lastName,
            email: this.currentUser.email,
            interests: this.currentUser.interests
        }
        this.userManager.updateUser(newUserInfo).then(result => {
            handleResultFn(result, this);
        }).catch(error => {
            error.isError = true;
            handleResultFn(error, this);
        }).finally(result => {
            this.saving = false;
        })
    }

    handleInterestResult(result, self) {
        self.addInterestSuccess = !result.isError;
    }

    handleUserResult(result, self) {
        if (result.isError) {
            self.showSaveError = true;
            if (error.status === 500) {
                self.errorMessage = "Unexpected error occurred."
            } else {
                self.errorMessage = "Failed to save user data."; //TODO: Replace with a message from the back end.
            }
        } else {
            self.showSaveSuccess = true;
            self.isDisabled = true;
        }
    }

    addInterest() {
        let foundInterest = this.currentUser.interests.find(interest => {
            return interest.keyword == this.selectedKeyword;
        })
        if (foundInterest) {
            this.hasInterest = true;
        } else {
            let matchingInterest = this.interests.filter(interest => {
                return interest.keyword == this.selectedKeyword;
            })
            this.currentUser.interests.push(matchingInterest[0]);
            this.saveUser(this.handleInterestResult);
        }
    }

    removeInterest(removedInterest) {
        this.currentUser.interests = this.currentUser.interests.filter(interest => {
            return interest.id !== removedInterest.id;
        })
        this.saveUser(this.handleInterestResult);
    }
}