package sqlinjectionspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sqlinjectionspring.entity.Query;
import sqlinjectionspring.form.AttackForm;
import sqlinjectionspring.service.MainService;
import sqlinjectionspring.service.QueryService;
import sqlinjectionspring.user.UserForm;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    private final QueryService queryService;
    private String wholeQuery;

    @GetMapping("/")
    public String index(Model model, @Valid AttackForm attackForm, BindingResult bindingResult) {
        model.addAttribute("result", "result");
        return "index";
    }

    @PostMapping("/attack")
    public String attack(Model model, @Valid AttackForm attackForm, BindingResult bindingResult) {

        String param = attackForm.getParam();
        String query =  "select * from user where id= '" + param + "' ";
        wholeQuery = query;

        return "redirect:/result";
    }

    @GetMapping("/result")
    public String result(Model model, @Valid AttackForm attackForm, BindingResult bindingResult) {
        attackForm.setParam("hahahah");
        model.addAttribute("result", wholeQuery);
        return "index";
    }

    @GetMapping("/signup")
    public String signup(UserForm userForm) {
        return "auth/signup";
    }

}