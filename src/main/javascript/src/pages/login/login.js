export class Login {
    
    constructor() {
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