package sqlinjectionspring.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserForm {

    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자 ID는 필수항목입니다.")
    private String id;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    @NotEmpty(message = "이름은 필수항목입니다.")
    private String name;

    @Email
    @NotEmpty(message = "이메일은 필수항목입니다.")
    private String email;
}
