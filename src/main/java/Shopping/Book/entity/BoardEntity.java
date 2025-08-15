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
@Table(name = "board")
@SequenceGenerator(name = "board_SEQ", sequenceName = "board_SEQ",
        initialValue = 1, allocationSize = 1)
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_SEQ")
    private Integer boardId;

    @Column(nullable = false, length = 20)
    private String boardSubject;

    @Column(length = 100)
    private String boardContent;

    private String boardImage;

    private String boardWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberEntity;


    // 비밀글 체크 여부
    private boolean secret;

    private String boardPassword;
}
