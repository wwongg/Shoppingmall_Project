package Shopping.Book.service;


import Shopping.Book.dto.ReviewDTO;
import Shopping.Book.entity.MemberEntity;
import Shopping.Book.entity.ReviewEntity;
import Shopping.Book.entity.ReviewcmtEntity;
import Shopping.Book.repository.MemberRepository;
import Shopping.Book.repository.ReviewRepository;
import Shopping.Book.repository.ReviewcmtRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final ReviewcmtRepository reviewcmtRepository;


    public void reviewInsert(ReviewDTO reviewDTO, Integer memberId) {
        reviewDTO.setReviewCount(0);
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow();

        ReviewEntity reviewEntity = modelMapper.map(reviewDTO, ReviewEntity.class);

        reviewEntity.setMemberEntity(memberEntity);
        reviewRepository.save(reviewEntity);
    }

    public void reviewUpdate(ReviewDTO reviewDTO, Integer memberId) {
        Integer id = reviewDTO.getReviewId();

        MemberEntity member = memberRepository.findById(memberId).orElseThrow();

        ReviewEntity review = reviewRepository.findById(id).orElseThrow();

        if (review.getMemberEntity().getMemberId().equals(memberId)) {
            ReviewEntity reviewEntity = modelMapper.map(reviewDTO, ReviewEntity.class);


            reviewEntity.setMemberEntity(member);
            reviewRepository.save(reviewEntity);
        }
    }

    public void reviewDelete(Integer reviewId) {
        List<ReviewcmtEntity> reviewcmtEntities = reviewcmtRepository.findByReviewId(reviewId);

        for (ReviewcmtEntity reviewcmt : reviewcmtEntities) {
            reviewcmtRepository.deleteById(reviewcmt.getReviewcmtID());
        }

        reviewRepository.deleteById(reviewId);
    }

    public Page<ReviewDTO> reviewList(Pageable pageable) {
        int currentPage = pageable.getPageNumber() - 1;
        int pageLimit = 10;

        Pageable page;
        page = PageRequest.of(currentPage, pageLimit, Sort.by(Sort.Direction.DESC, "reviewId"));

        Page<ReviewEntity> reviewEntities = reviewRepository.findAll(page);
        Page<ReviewDTO> reviewDTOS = reviewEntities.map(data -> modelMapper.map(data, ReviewDTO.class));

        return reviewDTOS;
    }

    public ReviewDTO reviewDetail(Integer reviewId, Integer memberId, String pandan) {
        if(pandan.equals("R")) {
            reviewRepository.reviewCount(reviewId);
        }

        ReviewEntity reviewEntity = reviewRepository.findById(reviewId).orElseThrow();

        ReviewDTO reviewDTO = null;

        if (reviewEntity.getMemberEntity().getMemberId().equals(memberId)) {
            reviewDTO = modelMapper.map(reviewEntity, ReviewDTO.class);
        }

        return reviewDTO;
    }

    public List<ReviewDTO> memberReview(Integer memberId) {
        List<ReviewEntity> reviewEntities = reviewRepository.findByMemberId(memberId);
        List<ReviewDTO> reviewDTOS = Arrays.asList(modelMapper.map(reviewEntities, ReviewDTO[].class));

        return reviewDTOS;
    }

}
