package sqlinjectionspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sqlinjectionspring.form.AttackForm;
import sqlinjectionspring.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private String wholeQuery;
    private List<Object> result;

    @GetMapping("/")
    public String index(Model model, @Valid AttackForm attackForm, BindingResult bindingResult) {
        if (wholeQuery == null) {
            model.addAttribute("wholeQuery", "null");
        } else {
            model.addAttribute("wholeQuery", wholeQuery);
        }

        return "index";
    }

    @PostMapping("/attack")
    public String attack(Model model, @Valid AttackForm attackForm, BindingResult bindingResult) {

        String param = attackForm.getParam();
        wholeQuery = "select * from user where id= '" + param + "' ";

        result = this.userService.getUsers(param);

        return "redirect:/result";
    }

    @GetMapping("/result")
    public String result(Model model, @Valid AttackForm attackForm, BindingResult bindingResult) throws JsonProcessingException {

        attackForm.setParam("where am i");

        String resultJson = toJson(result);

        model.addAttribute("wholeQuery", wholeQuery);
        model.addAttribute("result", resultJson);

        return "index";
    }

    public String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(object);

        return json;
    }


}