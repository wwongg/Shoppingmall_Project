package Shopping.Book.repository;

import Shopping.Book.entity.ReviewcmtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewcmtRepository extends JpaRepository<ReviewcmtEntity, Integer> {
    @Query("SELECT s FROM ReviewcmtEntity s WHERE s.reviewEntity.reviewId = :reviewId")
    List<ReviewcmtEntity> findByReviewId(Integer reviewId);

    @Query("select s from ReviewcmtEntity s where s.memberEntity.memberId = :memberId")
    ReviewcmtEntity findByMemberId(Integer memberId);
}
