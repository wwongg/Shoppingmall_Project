package Shopping.Book.controller;

import Shopping.Book.constant.RoleType;
import Shopping.Book.dto.BoardDTO;
import Shopping.Book.dto.MemberDTO;
import Shopping.Book.dto.ReviewDTO;
import Shopping.Book.service.BoardService;
import Shopping.Book.service.MemberService;
import Shopping.Book.service.ReviewService;
import Shopping.Book.util.PaginationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final BoardService boardService;
    private final ReviewService reviewService;

    @GetMapping("/member/list")
    public String listForm(@PageableDefault(page = 1) Pageable pageable, Model model) {

        Page<MemberDTO> memberDTOS = memberService.memberList(pageable);

        Map<String, Integer> page = PaginationUtil.Pagination(memberDTOS);

        model.addAttribute(page);
        model.addAttribute("list", memberDTOS);

        return "member/list";
    }

    @GetMapping("/member/insert")
    public String insertForm() {
        return "member/insert";
    }

    @PostMapping("/member/insert")
    public String insertProc(@Valid MemberDTO memberDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/member/insert";
        }

        try {
            memberDTO.setRoleType(RoleType.USER);

            memberService.memberInsert(memberDTO);
        } catch (Exception e) {
            return "redirect:/member/insert";
        }

        return "redirect:/login";
    }

    @GetMapping("/member/update")
    public String updateForm(Integer id, Model model) {
        MemberDTO memberDTO = memberService.memberDetail(id);

        model.addAttribute("data", memberDTO);

        return "member/update";
    }

    @PostMapping("/member/update")
    public String updateProc(MemberDTO memberDTO) {
        memberService.memberUpdate(memberDTO);

        return "redirect:/";
    }

    @GetMapping("/member/delete")
    public String deleteProc(Integer id) {
        memberService.memberDelete(id);

        return "redirect:/";
    }

    @GetMapping("/member/detail")
    public String readProc(Integer id, Model model) {
        MemberDTO memberDTO = memberService.memberDetail(id);

        model.addAttribute("data", memberDTO);

        return "member/detail";
    }

    @GetMapping("/mypage")
    public String myPageForm(@AuthenticationPrincipal User user,
                             Model model) {
        String memberEmail = user.getUsername();
        MemberDTO memberDTO = memberService.detail(memberEmail);

        List<BoardDTO> boardDTOS = boardService.memberBoard(memberDTO.getMemberId());

        List<ReviewDTO> reviewDTOS = reviewService.memberReview(memberDTO.getMemberId());

        model.addAttribute("board", boardDTOS);
        model.addAttribute("review", reviewDTOS);
        model.addAttribute("data", memberDTO);

        return "member/mypage";
    }

}
