package sysc4806.graduateAdmissions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin(maxAge = 3600)
public class HomeController {
    @RequestMapping(method = RequestMethod.GET, path="/")
    public String home(Model model) {
        return "home";
    }
}
