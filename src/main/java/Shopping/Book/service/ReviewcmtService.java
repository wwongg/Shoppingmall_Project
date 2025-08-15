package Shopping.Book.service;


import Shopping.Book.dto.ReviewcmtDTO;
import Shopping.Book.entity.ReviewEntity;
import Shopping.Book.entity.ReviewcmtEntity;
import Shopping.Book.repository.ReviewRepository;
import Shopping.Book.repository.ReviewcmtRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewcmtService {

    private final ReviewcmtRepository reviewcmtRepository;
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    public void reviewcmtInsert(ReviewcmtDTO reviewcmtDTO, Integer reviewId) {
        Optional<ReviewEntity> data = reviewRepository.findById(reviewId);

        ReviewEntity reviewEntity = data.orElseThrow();

        ReviewcmtEntity reviewcmtEntity = modelMapper.map(reviewcmtDTO, ReviewcmtEntity.class);

        reviewcmtEntity.setReviewEntity(reviewEntity);
        reviewcmtRepository.save(reviewcmtEntity);
    }

    public void reviewcmtUpdate(ReviewcmtDTO reviewcmtDTO, Integer reviewId) {
        Optional<ReviewEntity> date = reviewRepository.findById(reviewId);

        ReviewEntity reviewEntity = date.orElseThrow();

        ReviewcmtEntity reviewcmtEntity = modelMapper.map(reviewcmtDTO, ReviewcmtEntity.class);

        reviewcmtEntity.setReviewEntity(reviewEntity);
        reviewcmtRepository.save(reviewcmtEntity);
    }

    public void reviewcmtDelete(Integer reviewcmtId) {
        reviewcmtRepository.deleteById(reviewcmtId);
    }

    public List<ReviewcmtDTO> reviewcmtlist(Integer reviewId) {
        List<ReviewcmtEntity> reviewcmtEntities = reviewcmtRepository.findByReviewId(reviewId);

        List<ReviewcmtDTO> reviewcmtDTOS = Arrays.asList(modelMapper.map(reviewcmtEntities, ReviewcmtDTO[].class));

        return reviewcmtDTOS;
    }

    public ReviewcmtDTO reviewcmtDetail(Integer reviewcmtId) {
        Optional<ReviewcmtEntity> reviewcmtEntity = reviewcmtRepository.findById(reviewcmtId);
        ReviewcmtDTO reviewcmtDTO = modelMapper.map(reviewcmtEntity, ReviewcmtDTO.class);

        return reviewcmtDTO;
    }
}
