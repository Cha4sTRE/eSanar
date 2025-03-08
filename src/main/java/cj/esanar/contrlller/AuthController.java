package cj.esanar.contrlller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enf")
@PreAuthorize("denyAll()")
public class AuthController {

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
