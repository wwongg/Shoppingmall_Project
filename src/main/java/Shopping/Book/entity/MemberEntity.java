package Shopping.Book.entity;


import Shopping.Book.constant.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "member")
@SequenceGenerator(name = "member_SEQ", sequenceName = "member_SEQ", initialValue = 1,
allocationSize = 1)
public class MemberEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_SEQ")
    private Integer memberId;

    @Column(length = 20, nullable = false)
    private String memberEmail;

    @Column(length = 20, nullable = false)
    private String memberPassword;

    @Column(length = 10)
    private String memberName;

    @Column(length = 20)
    private String memberPhone;

    @Column(length = 30)
    private String memberAddress;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
