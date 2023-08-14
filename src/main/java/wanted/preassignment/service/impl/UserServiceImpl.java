package wanted.preassignment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.preassignment.config.jwt.JwtProvider;
import wanted.preassignment.config.jwt.TokenDto;
import wanted.preassignment.dto.request.JoinUserRequest;
import wanted.preassignment.entity.User.User;
import wanted.preassignment.entity.User.UserRole;
import wanted.preassignment.exception.CustomException;
import wanted.preassignment.exception.ErrorCode;
import wanted.preassignment.repository.UserRepository;
import wanted.preassignment.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;

    @Transactional
    @Override
    public Long join(JoinUserRequest joinUserRequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = joinUserRequest.getPassword();
        joinUserRequest.setPassword(bCryptPasswordEncoder.encode(rawPassword));

        userRepository.findByEmail(joinUserRequest.getEmail()).ifPresent(
                ex -> {
                    throw new CustomException(ErrorCode.EXIST_USER_EMAIL);
                }
        );

        return userRepository.save(
                User.builder()
                        .email(joinUserRequest.getEmail())
                        .password(joinUserRequest.getPassword())
                        .name(joinUserRequest.getName())
                        .role(UserRole.ROLE_USER)
                        .build()
        ).getId();
    }

    @Override
    public TokenDto login(String email, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtProvider.generateToken(authentication);

        return tokenDto;
    }

    @Override
    public User userInfo(User user) {

        return userRepository.findById(user.getId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );
    }

}
