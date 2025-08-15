package Shopping.Book.service;

import Shopping.Book.dto.MemberDTO;
import Shopping.Book.entity.CartEntity;
import Shopping.Book.entity.MemberEntity;
import Shopping.Book.repository.CartRepository;
import Shopping.Book.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    public void memberInsert(MemberDTO memberDTO) throws IllegalAccessException {
        Optional<MemberEntity> member = Optional.ofNullable(memberRepository.findByMemberEmail(memberDTO
                .getMemberEmail()));

        if (member.isEmpty()) {
            MemberEntity memberEntity = modelMapper.map(memberDTO,
                    MemberEntity.class);

            memberRepository.save(memberEntity);
        } else {
            throw new IllegalAccessException("이미 가입된 회원입니다.");
        }
    }

    public void memberUpdate(MemberDTO memberDTO) {
        MemberEntity memberEntity = modelMapper.map(memberDTO,
                MemberEntity.class);
        memberRepository.save(memberEntity);
    }

    public void memberDelete(Integer memberId) {
        CartEntity cart = cartRepository.findByMemberId(memberId);
        cartRepository.deleteById(cart.getCartId());

        memberRepository.deleteById(memberId);
    }

    public Page<MemberDTO> memberList(Pageable pageable) {
        int cutPage = pageable.getPageNumber() - 1;
        int pageCnt = 10;

        Pageable page = PageRequest.of(cutPage, pageCnt, Sort.by(Sort.Direction.DESC, "memberId"));

        // 데이터베이스 결과 그대로를 담은 JPA엔티티 객체 목록(DB에서 읽어온 원본데이터)
        Page<MemberEntity> memberEntities = memberRepository.findAll(page);

        // 엔티티를 가공해서 필요한 정보만 담은 응답용 데이터 객체 목록(화면/응답에 맞게 가공된 데이터)
        Page<MemberDTO> memberDTOS = memberEntities.map(data ->
                modelMapper.map(data, MemberDTO.class));


        return memberDTOS;
    }

    public MemberDTO memberDetail(Integer memberId) {
        MemberEntity memberEntity =
                memberRepository.findById(memberId).orElseThrow();

        MemberDTO memberDTO = modelMapper.map(memberEntity, MemberDTO.class);

        return memberDTO;
    }

    public MemberDTO detail(String memberEmail) {
        MemberEntity member = memberRepository.findByMemberEmail(memberEmail);
        MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);

        return memberDTO;
    }
}
