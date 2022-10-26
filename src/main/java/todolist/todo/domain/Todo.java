package todolist.todo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Todo {

    @Id @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    @Column(nullable = false)
    private String title;
    private String describe;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    @Enumerated(EnumType.STRING)
    private TodoStatus status; //[todo, doing, done]

    @Builder
    public Todo(String title, String describe) {
        this.title = title;
        this.describe = describe;
        this.createDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
        this.status = TodoStatus.TODO;
    }

    //==비즈니스 로직==//
    /**
     * 내용 변경
     */
    public void update(String title, String describe) {
        Boolean changed = false;

        if (title != null && !title.isEmpty()) {
            this.title = title;
            changed = true;
        }
        if (describe != null && !describe.isEmpty()) {
            this.describe = describe;
            changed = true;
        }

        if (changed) {
            this.modifiedDate = LocalDateTime.now();
        }
    }

    /**
     * 상태 변경
     */
    public void changeStatus(TodoStatus status) {
        this.status = status;
        this.modifiedDate = LocalDateTime.now();
    }
}
