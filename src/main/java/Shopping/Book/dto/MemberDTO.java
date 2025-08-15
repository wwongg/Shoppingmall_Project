package Shopping.Book.dto;

import Shopping.Book.constant.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    private Integer memberId;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email
    private String memberEmail;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String memberPassword;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(min = 2, max = 10, message = "이름은 2자 이상 10자 이하여야 합니다.")
    private String memberName;

    private String memberPhone;

    @NotBlank(message = "주소는 필수입니다.")
    private String memberAddress;

    private RoleType roleType;

    // 등록일
    private LocalDateTime regDate;

    // 수정일
    private LocalDateTime modDate;
}
