package Shopping.Book.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "review")
@SequenceGenerator(name = "review_SEQ", sequenceName = "review_SEQ", initialValue = 1, allocationSize = 1)
public class ReviewEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_SEQ")
    private Integer reviewId;

    @Column(length = 20, nullable = false)
    private String reviewSubject;

    @Column(length = 100)
    private String reviewContent;

    private String reviewImage;

    @Column(nullable = false)
    private Integer reviewScore;

    private String reviewWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberEntity;
}
