package todolist.todo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id; //pk

    @Column(unique = true, nullable = false)
    private String username; //id

    @Column(nullable = false)
    private String nickname; //닉네임

    @Column(nullable = false)
    private String password; //password
    private String email; //email

    private LocalDateTime joinDate; //가입일
    private LocalDateTime modifiedDate; //수정일

    @Builder
    public User(String username, String nickname, String password, String email) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.joinDate = LocalDateTime.now();
    }

    //==비즈니스 로직==//
    public void updatePassword(String password) {
        if (password != null && !password.isEmpty()) {
            this.password = password;
            this.modifiedDate = LocalDateTime.now();
        }
    }

    public void updateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            this.email = email;
            this.modifiedDate = LocalDateTime.now();
        }
    }
}
