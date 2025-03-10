package cj.esanar.contrlller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
@PreAuthorize("permitAll()")
public class WelcomeController {

    @GetMapping("index")
    public String index() {
        return "index";
    }

}
