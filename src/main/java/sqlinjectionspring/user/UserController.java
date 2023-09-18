package sqlinjectionspring.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;



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

        return "redirect:/api";
    }
}
