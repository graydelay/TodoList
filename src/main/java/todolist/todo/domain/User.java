package todolist.todo.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User extends TimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; //pk

    @Column(unique = true, nullable = false, length = 30)
    private String username; //id

    @Column(nullable = false)
    private String nickname; //닉네임

    @Column(nullable = false, length = 100)
    private String password; //password

    @Column(nullable = false, length = 50)
    private String email; //email

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //==회원 정보 수정==//
    public void modify(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }
}
