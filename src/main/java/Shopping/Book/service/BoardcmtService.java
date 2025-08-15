package Shopping.Book.service;


import Shopping.Book.dto.BoardcmtDTO;
import Shopping.Book.entity.BoardEntity;
import Shopping.Book.entity.BoardcmtEntity;
import Shopping.Book.entity.MemberEntity;
import Shopping.Book.repository.BoardRepository;
import Shopping.Book.repository.BoardcmtRepository;
import Shopping.Book.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardcmtService {
    private final BoardcmtRepository boardcmtRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    //삽입
    public void boardcmtInsert(BoardcmtDTO boardcmtDTO, Integer boardId, Integer memberId) {

        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow();

        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow();

        BoardcmtEntity boardcmtEntity = modelMapper.map(boardcmtDTO, BoardcmtEntity.class);

        boardcmtEntity.setBoardEntity(boardEntity);
        boardcmtEntity.setMemberEntity(memberEntity);
        boardcmtRepository.save(boardcmtEntity);
    }

    //수정
    public void boardcmtUpdate(BoardcmtDTO boardcmtDTO) {

        MemberEntity member = memberRepository.findById(boardcmtDTO.getMemberId()).orElseThrow();

        BoardEntity boardDTO = boardRepository.findById(boardcmtDTO.getBoardId()).orElseThrow();

        BoardcmtEntity boardcmtEntity = boardcmtRepository.findById(boardcmtDTO.getBoardcmtId()).orElseThrow();

        if (boardcmtEntity != null) {

            BoardcmtEntity boardcmt = modelMapper.map(boardcmtDTO, BoardcmtEntity.class);
            boardcmt.setMemberEntity(member);
            boardcmt.setBoardEntity(boardDTO);
            boardcmtRepository.save(boardcmt);
        }
    }

    //삭제
    public void boardcmtDelete(Integer boardcmtId) {
        boardcmtRepository.deleteById(boardcmtId);
    }

    //전체 조회
    public List<BoardcmtDTO> boardcmtlist(Integer boardId) {
        List<BoardcmtEntity> boardcmtEntities = boardcmtRepository.findByBoardId(boardId);

        List<BoardcmtDTO> boardcmtDTOS = Arrays.asList(modelMapper.
                map(boardcmtEntities, BoardcmtDTO[].class));

        return boardcmtDTOS;
    }

    //개별조회
    public BoardcmtDTO boardcmtDetail(Integer boardcmtId, Integer boardId, Integer memberId) {

        BoardcmtEntity boardcmtEntity = boardcmtRepository.findById(boardcmtId).orElseThrow();

        BoardcmtDTO boardcmtDTO = null;
        if (boardcmtEntity.getMemberEntity().getMemberId().equals(memberId) &&
                boardcmtEntity.getBoardEntity().getBoardId().equals(boardId)) {
            boardcmtDTO = modelMapper.map(boardcmtEntity, BoardcmtDTO.class);
        }

        return boardcmtDTO;
    }
}
