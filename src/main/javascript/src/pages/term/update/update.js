import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { TermManager } from 'managers/term-manager';
import { SeasonManager } from 'managers/season-manager';

@inject(Router, TermManager, SeasonManager)
export class updateTerm {

    constructor(router, termManager, seasonManager) {
        this.router = router;
        this.scrollTop = 0;
		this.seasonFilter = "all"
        this.seasonManager = seasonManager;
        this.termManager = termManager;
    }

    attached() {
    	this.termManager.getTerms().then(response => {
			this.terms = response;
		});
        this.seasonManager.getSeasons().then(response => {
			this.seasons = response;
		});
    }

	deleteTerm(termId){
		this.termManager.deleteTerm(termId).then(()=>{
			this.termManager.getTerms().then(response => {
				this.terms = response;
			});
		});
	}

	updateTerm(id, termActivity, termDeadline, termSeason, termYear){
		this.myTermDTO = {
			termId: id,
			active: termActivity,
        	deadline: termDeadline,
        	season: termSeason,
        	year: termYear
        }

		this.termManager.updateTerm(this.myTermDTO).then(()=>{
			this.termManager.getTerms().then(response => {
				this.terms = response;
			});
		});
	}
}
