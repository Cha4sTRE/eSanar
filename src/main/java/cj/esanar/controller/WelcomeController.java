package cj.esanar.controller;


import cj.esanar.service.implement.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@PreAuthorize("permitAll()")
public class WelcomeController {

    @Autowired
    UserDetailServiceImpl userDetailService;

    @GetMapping("index")
    public String index() {
        return "index";
    }

}
