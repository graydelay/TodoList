package todolist.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import todolist.todo.domain.User;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    private Long id;

    @NotBlank
    private String username; //id

    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String email;

    /**
     * DTO -> Entity 변환
     */
    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();
    }
}
