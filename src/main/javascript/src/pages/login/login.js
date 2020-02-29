import { inject } from 'aurelia-framework';
import { Router } from "aurelia-router"

@inject(Router)
export class Login {
    
    constructor(router) {
        this.router = router;
        this.scrollTop = 0;
    }

    attached() {
    }

    scrollFn() {
        window.scrollTo({top: 0, behavior: 'smooth'});
        document.getElementById("top").scrollIntoView({ 
            behavior: 'smooth'
        });
    }

}