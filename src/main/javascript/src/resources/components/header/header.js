import { inject, observable, bindable } from 'aurelia-framework';
import { Router } from "aurelia-router"

@inject(Router)
export class Header {

    @bindable scroll_position;
    @bindable scroll_fn;

    constructor(router) {
        this.router = router;
        this.router.routes.forEach((route, index) => {
            if (route.name === "home") {
                route.char = "H";
            } else {
                route.char = route.title.charAt(0);
            }
        })
        this.screenWidth = screen.width;
        this.showNavOptions = false;
    }

    scroll_positionChanged(newValue, oldValue) {
        if (newValue > 90) {
            this.showNavOptions = false;
        }
    }

    scrollToTop() {
        // document.getElementById("top").scrollIntoView({ 
        //     behavior: 'smooth'
        // });
        // window.scrollTo({top: 0, behavior: 'smooth'});
        
    }
}