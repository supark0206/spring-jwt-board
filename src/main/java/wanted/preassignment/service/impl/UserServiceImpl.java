package wanted.preassignment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.preassignment.dto.request.JoinUserRequest;
import wanted.preassignment.entity.User.User;
import wanted.preassignment.entity.User.UserRole;
import wanted.preassignment.repository.UserRepository;
import wanted.preassignment.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(email)
        );
    }

    @Transactional
    @Override
    public Long join(JoinUserRequest joinUserRequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = joinUserRequest.getPassword();
        joinUserRequest.setPassword(bCryptPasswordEncoder.encode(rawPassword));

        userRepository.findByEmail(joinUserRequest.getEmail()).ifPresent(
                ex -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
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

}
