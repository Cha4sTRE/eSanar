package cj.esanar.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @PreAuthorize("permitAll()")
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
