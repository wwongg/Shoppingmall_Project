package Shopping.Book.repository;

import Shopping.Book.entity.BoardcmtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardcmtRepository extends
        JpaRepository<BoardcmtEntity, Integer> {
    @Query("SELECT s FROM BoardcmtEntity s WHERE s.boardEntity.boardId = :boardId")
    List<BoardcmtEntity> findByBoardId(Integer boardId);

    @Query("select s from BoardcmtEntity s where s.memberEntity.memberId = :memberId")
    BoardcmtEntity findByMemberId(Integer memberId);
}
