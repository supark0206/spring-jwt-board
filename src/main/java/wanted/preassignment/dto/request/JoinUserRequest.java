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
    @ApiModelProperty(value = "회원 아이디", example = "xxx@xxxx", required = true)
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다")
    private String email;

    @ApiModelProperty(value = "비밀번호", example = "8자 이상 입력하세요.", required = true)
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")

    private String password;

    @ApiModelProperty(value = "회원 이름", example = "회원 이름", required = true)
    private String name;
}
