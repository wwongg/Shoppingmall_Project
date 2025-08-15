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
public class BoardDTO {
    private Integer boardId;

    @NotBlank(message = "제목은 필수입니다.")
    private String boardSubject;

    private String boardContent;

    private String boardImage;

    private String boardWriter;

    private Integer memberId;

    private boolean secret;

    private String boardPassword;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
