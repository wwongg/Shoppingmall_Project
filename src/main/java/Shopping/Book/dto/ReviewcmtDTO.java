package Shopping.Book.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewcmtDTO {

    private Integer reviewcmtId;

    @NotBlank(message = "내용은 필수입니다.")
    private String reviewcmtBody;

    private String reviewcmtWriter;

    private Integer memberId;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private Integer reviewId;

}
