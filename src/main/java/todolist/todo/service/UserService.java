package todolist.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.todo.domain.Todo;
import todolist.todo.domain.User;
import todolist.todo.dto.UserRequestDto;
import todolist.todo.dto.UserResponseDto;
import todolist.todo.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    /* 회원 CREATE */
    @Transactional
    public void join(UserRequestDto dto) {
        dto.setPassword(encoder.encode(dto.getPassword())); // password encoding
        User user = dto.toEntity();

        validateDuplicateUser(user); //중복 회원 검증
        userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        Optional<User> findUsers = userRepository.findByUsername(user.getUsername());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public Optional<User> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /* 회원 UPDATE */
    @Transactional
    public UserResponseDto modify(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다"));

        String encPassword = encoder.encode(dto.getPassword());
        user.modify(dto.getNickname(), encPassword, dto.getEmail());
        return new UserResponseDto(user);
    }

    /* DELETE */
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다. id=" + id));

        userRepository.delete(user);
    }
}
