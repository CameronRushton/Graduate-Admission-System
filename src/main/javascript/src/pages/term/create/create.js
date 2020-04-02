import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"
import { TermManager } from 'managers/term-manager';
import { SeasonManager } from 'managers/season-manager';

@inject(Router, TermManager, SeasonManager)
export class createTerm {

    constructor(router, termManager, seasonManager) {
        this.router = router;
        this.scrollTop = 0;
        this.termManager = termManager;
        this.seasonManager = seasonManager

    }

    activate() {
    	this.seasonManager.getSeasons().then(response => {
    			this.seasons = response;
    		});
    }

	submitHandler(id, termActivity, termDeadline, termSeason, termYear){
		this.myTermDTO = {
			termId: id,
			active: termActivity,
			deadline: termDeadline,
			season: termSeason,
			year: termYear
		}
		this.termManager.createTerm(this.myTermDTO);
	}
}
