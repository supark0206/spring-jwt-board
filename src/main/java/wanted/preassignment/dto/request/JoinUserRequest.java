package wanted.preassignment.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinUserRequest {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^.{8,}",message = "비밀번호는 8글자 이상이어야합니다.")
    private String password;

    private String name;
}
