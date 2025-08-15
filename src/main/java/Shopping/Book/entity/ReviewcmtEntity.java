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
@Table(name = "reviewcmt")
@SequenceGenerator(name = "reviewcmt_SEQ", sequenceName = "reviewcmt_SEQ",
        initialValue = 1, allocationSize = 1)
public class ReviewcmtEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewcmt_SEQ")
    private Integer reviewcmtID;

    @Column(nullable = false, length = 100)
    private String reviewcmtBody;

    private String reviewcmtWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewEntity reviewEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberEntity;
}
