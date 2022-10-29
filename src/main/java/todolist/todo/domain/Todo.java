package todolist.todo.domain;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Todo extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String describe;

    @Column(nullable = false)
    private String writer;

    @Enumerated(EnumType.STRING)
    private TodoStatus status; //[todo, doing, done]

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //==비즈니스 로직==//
    @PrePersist
    public void onPrePersist() {
        status = TodoStatus.TODO;
    }

    /* 수정 */
    public void update(String title, String describe, TodoStatus status) {
        this.title = title;
        this.describe = describe;
        this.status = status;
    }
}
