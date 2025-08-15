package Shopping.Book.repository;


import Shopping.Book.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    Page<BoardEntity> findByBoardSubjectContaining(String keyword, Pageable pageable);

    Page<BoardEntity> findByBoardContentContaining(String keyword, Pageable pageable);

    Page<BoardEntity> findByBoardWriterContaining(String keyword, Pageable pageable);

    @Query("SELECT s FROM BoardEntity s WHERE s.boardSubject LIKE %:keyword%" +
            " or s.boardContent LIKE %:keyword%")
    Page<BoardEntity> findByBoardSCContaining(String keyword, Pageable pageable);

    @Query("select w from BoardEntity w where w.memberEntit.memberId = :memberId")
    List<BoardEntity> findByMemberId(Integer memberId);

    @Query("select w from BoardEntity w where w.memberEntity.memberId = :memberId")
    BoardEntity findByMember(Integer memberId);
}
