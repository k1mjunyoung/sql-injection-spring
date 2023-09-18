package sqlinjectionspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sqlinjectionspring.user.UserForm;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String redirect() {
        return "redirect:/signup";
    }

    @GetMapping("/signup")
    public String signup(UserForm userForm) {
        return "auth/signup";
    }

}