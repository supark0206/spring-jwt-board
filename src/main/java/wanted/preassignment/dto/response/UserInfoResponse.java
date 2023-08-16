package wanted.preassignment.dto.response;

import lombok.Data;
import wanted.preassignment.entity.User.User;

@Data
public class UserInfoResponse {
    private Long id;
    private String email;
    private String name;
    private String role;

    public UserInfoResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.role = String.valueOf(user.getRole());
    }
}
