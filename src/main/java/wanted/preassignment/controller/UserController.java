package wanted.preassignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.preassignment.dto.request.JoinUserRequest;
import wanted.preassignment.dto.response.JoinUserResponse;
import wanted.preassignment.service.UserService;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<JoinUserResponse> join(@Valid @RequestBody JoinUserRequest joinMemberRequest) {
        Long id = userService.join(joinMemberRequest);
        String message = "회원가입에 성공하였습니다.";

        return ResponseEntity.ok(new JoinUserResponse(id,message));
    }


}
