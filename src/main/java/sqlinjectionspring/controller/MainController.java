package sqlinjectionspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

//    @PostMapping("/attack")
//    public String attack(Model model, @Valid AttackForm attackForm, BindingResult bindingResult) {
//
//        String param = attackForm.getParam();
//        wholeQuery = "select * from user where id= '" + param + "' ";
//
//        result = this.userService.getUsers(param);
//
//        return "redirect:/result";
//    }
//
//    @GetMapping("/result")
//    public String result(Model model, @Valid AttackForm attackForm, BindingResult bindingResult) throws JsonProcessingException {
//
//        attackForm.setParam("where am i");
//
//        String resultJson = toJson(result);
//
//        model.addAttribute("wholeQuery", wholeQuery);
//        model.addAttribute("result", resultJson);
//
//        return "index";
//    }
//
//    public String toJson(Object object) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        String json = objectMapper.writeValueAsString(object);
//
//        return json;
//    }


}