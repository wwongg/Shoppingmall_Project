package Shopping.Book.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardcmtDTO {
    private Integer boardcmtId;

    //고객센터 댓글 내용
    @NotBlank(message = "내용은 필수입니다.")
    private String boardcmtBody;

    //고객센터 댓글 작성자
    private String boardcmtWriter;

    //추가사항
    //회원 번호
    private Integer memberId;

    //등록일
    private LocalDateTime regDate;

    //수정일
    private LocalDateTime modDate;

    //고객센터 게시판 번호
    private Integer boardId;
}
