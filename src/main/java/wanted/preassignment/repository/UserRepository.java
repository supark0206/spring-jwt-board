package wanted.preassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.preassignment.entity.User.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
