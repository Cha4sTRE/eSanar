package cj.esanar.contrlller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@PreAuthorize("permitAll()")
public class WelcomeController {

    @GetMapping("index")
    public String index() {
        return "index";
    }

}
