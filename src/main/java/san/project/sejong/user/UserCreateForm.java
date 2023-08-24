package san.project.sejong.user;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    @Email
    private String email;
    private String password1;
    private String password2;
    private String nickname;
}
