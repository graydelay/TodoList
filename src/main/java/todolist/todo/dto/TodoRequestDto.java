package todolist.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import todolist.todo.domain.Todo;
import todolist.todo.domain.TodoStatus;
import todolist.todo.domain.User;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoRequestDto {

    private Long id;
    private String title;
    private String describe;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private TodoStatus status;
    private User user;

    /**
     * DTO -> Entity 변환
     */
    public Todo toEntity() {
        return Todo.builder()
                .id(id)
                .title(title)
                .describe(describe)
                .status(status)
                .writer(user.getUsername())
                .user(user)
                .build();
    }
}
