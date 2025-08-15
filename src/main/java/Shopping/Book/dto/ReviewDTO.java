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
public class ReviewDTO {

    private Integer reviewId;

    @NotBlank(message = "제목은 필수입니다.")
    private String reviewSubject;

    private String reviewImage;

    private Integer reviewScore;

    private Integer reviewCount;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private String reviewWriter;

    private Integer memberId;
}
