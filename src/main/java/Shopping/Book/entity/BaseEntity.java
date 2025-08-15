package Shopping.Book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "regDate", nullable = false, updatable = false)
    @CreatedDate    // entity가 생성될 때 자동으로 현재 날짜와 시간을 저장
    private LocalDateTime regDate;

    @Column(name = "modDate")
    @LastModifiedDate   // entity가 수정될 때 자동으로 현재 날짜와 시간을 저장
    private LocalDateTime modDate;
}
