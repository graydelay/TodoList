package todolist.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.todo.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User findByNickname(String nickname);
}
