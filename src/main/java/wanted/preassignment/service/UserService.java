package wanted.preassignment.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import wanted.preassignment.config.annotation.LoginUser;
import wanted.preassignment.config.jwt.TokenDto;
import wanted.preassignment.dto.request.JoinUserRequest;
import wanted.preassignment.entity.User.User;

public interface UserService{
    public Long join(JoinUserRequest joinMemberRequest);

    public TokenDto login(String email, String password);

    public User userInfo(User user);
}
