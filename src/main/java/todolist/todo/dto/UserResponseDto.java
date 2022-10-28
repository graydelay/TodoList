package todolist.todo.dto;

import lombok.Data;
import todolist.todo.domain.User;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {

    private final Long id;
    private final String username;
    private final String nickname;
    private final String email;
    private final LocalDateTime modifiedDate;

    /**
     * Entity -> Dto
     */
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.modifiedDate = user.getModifiedDate();
    }
}
