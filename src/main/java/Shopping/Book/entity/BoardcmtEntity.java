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
@Table(name = "boardcmt")
@SequenceGenerator(name = "boardcmt_SEQ", sequenceName = "boardcmt_SEQ",
        initialValue = 1, allocationSize = 1)
public class BoardcmtEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardcmt_SEQ")
    private Integer boardcmtId;

    @Column(nullable = false, length = 100)
    private String boardcmtBody;

    private String boardcmtWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberEntity;
}
