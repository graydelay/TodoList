package todolist.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import todolist.todo.domain.Todo;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoResponseDto {

    private Long id;
    private String title;
    private String describe;
    private String writer;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private Long userId;

    /**
     * Entity -> Dto 변환
     */
   public TodoResponseDto(Todo todo) {
       this.id = todo.getId();
       this.title = todo.getTitle();
       this.describe = todo.getDescribe();
       this.writer = todo.getWriter();
       this.createDate = todo.getCreatedDate();
       this.modifiedDate = todo.getModifiedDate();
       this.userId = todo.getUser().getId();
   }
}
