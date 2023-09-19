package sqlinjectionspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sqlinjectionspring.form.UserForm;
import sqlinjectionspring.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserForm userForm) {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserForm userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }

        if (!userForm.getPassword().equals(userForm.getPassword2())) {

            bindingResult.rejectValue("password2", "passwordIncorrect", "비밀번호 확인이 일치하지 않습니다.");

            return "auth/signup";
        }

        try {
            userService.create(userForm.getId(), userForm.getPassword(), userForm.getName(), userForm.getEmail());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();;
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "auth/signup";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "auth/signup";
        }

        return "redirect:/";
    }
}
