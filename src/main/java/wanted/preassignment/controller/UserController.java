package wanted.preassignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import wanted.preassignment.config.annotation.LoginUser;
import wanted.preassignment.config.jwt.TokenDto;
import wanted.preassignment.dto.request.JoinUserRequest;
import wanted.preassignment.dto.request.UserLoginRequest;
import wanted.preassignment.dto.response.ResultResponse;
import wanted.preassignment.dto.response.UserInfoResponse;
import wanted.preassignment.entity.User.User;
import wanted.preassignment.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ResultResponse> join(@Valid @RequestBody JoinUserRequest joinMemberRequest) {
        Long id = userService.join(joinMemberRequest);
        String message = "회원가입에 성공하였습니다.";

        return ResponseEntity.ok(new ResultResponse(id,message));
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserLoginRequest userLoginRequest) {
        String memberId = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();
        return userService.login(memberId, password);
    }

    @GetMapping("/user")
    public ResponseEntity<UserInfoResponse> userInfo(@LoginUser User user) {

        UserInfoResponse userInfo = new UserInfoResponse(userService.userInfo(user));

        return ResponseEntity.ok(userInfo);
    }

}
