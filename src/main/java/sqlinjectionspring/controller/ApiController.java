package sqlinjectionspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sqlinjectionspring.entity.User;
import sqlinjectionspring.form.AttackForm;
import sqlinjectionspring.service.MainService;
import sqlinjectionspring.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "test", description = "테스트 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;

    private String wholeQuery;
    private String Query;
    private List<Object> result;

    @GetMapping("/user")
    public List<Object> test() {

        List<Object> user = this.userService.getUsers("'test01' or id is not NULL");

        return user;
    }

    @PostMapping("/attack")
    public String attack(@RequestBody String param, @Valid AttackForm attackForm, BindingResult bindingResult) {

        param = attackForm.getParam();
        this.wholeQuery = "select * from user where id= '" + param + "' ";

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
