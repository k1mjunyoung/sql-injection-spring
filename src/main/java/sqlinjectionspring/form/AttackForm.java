package sqlinjectionspring.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AttackForm {

    @NotEmpty
    private String param;

}
