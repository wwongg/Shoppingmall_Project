package Shopping.Book.repository;

import Shopping.Book.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

    @Modifying
    @Query("UPDATE ReviewEntity u SET u.reviewCount = u.reviewCount+1" +
            "WHERE u.reviewId = :reviewId")
    void reviewCount(Integer reviewId);

    @Query("select s from ReviewEntity s where s.memberEntity.memberId = :memberId")
    List<ReviewEntity> findByMemberId(Integer memberId);

    @Query("select w from ReviewEntity w where w.reviewId = :reviewId and w.memberEntity.memberId = :memberId")
    ReviewEntity findByReviewIdAndMemberId(Integer reviewId, Integer memberId);
}
