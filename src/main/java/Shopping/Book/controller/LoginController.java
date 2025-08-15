package Shopping.Book.controller;


import Shopping.Book.dto.MemberDTO;
import Shopping.Book.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm() {
        return "login/form";
    }

    @GetMapping("/find/email")
    public String email() {
        return "login/email";
    }

    @PostMapping("/find/email")
    public String emailProc(Model model, String memberName, String memberPassword,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/find/email";
        }

        MemberDTO memberDTO = (MemberDTO) loginService.findEmail(memberPassword, memberName);
        if (memberDTO == null) {
            bindingResult.reject("Not found", "비밀번호 또는 이름이 일치하지 않습니다.");

            return "redirect:/find/email";
        }

        model.addAttribute("data", memberDTO);

        return "login/resultemail";
    }
}
