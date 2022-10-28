package todolist.todo.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    //==비즈니스 로직==//
    /* 저장하기 전에 실행 */
    @PrePersist
    public void onPrePersist() {
        createdDate = LocalDateTime.now();
        modifiedDate = createdDate;
    }

    /* 업데이트 하기 전에 실행 */
    @PreUpdate
    public void onPreUpdate() {
        modifiedDate = LocalDateTime.now();
    }
}
