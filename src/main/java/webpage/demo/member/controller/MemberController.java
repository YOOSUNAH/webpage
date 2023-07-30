package webpage.demo.member.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webpage.demo.member.domain.Member;
import webpage.demo.member.domain.MemberRepository;

@Slf4j
@Controller
@RequestMapping("/users")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
//        model.addAttribute("loginUser", getCurrentUser());
        return "login";
    }

    @PostMapping("/login")
    public String login(
            HttpSession session,
            @RequestParam String username,
            @RequestParam String password
    ) {

        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            return "redirect:/users/login" + "?isValid=false";
        }
        if (!member.getPassword().equals(password)) {
            return "redirect:/users/login" + "?isValid=false";
        }

        session.setAttribute("member", member);
        return "redirect:/";
    }


    /*         모두 빈값이거나 null 이어서는 안됨
               username - 영문 숫자 혼합 5글자 이상
               nickname - 영문 숫자 혼합 5글자 이상
               password - 영문, 숫자 각각 최소 한글자 포함 총 8글자 이상
               passwordCheck - password 와 반드시 일치해야 함.

                */
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String nickname,
            @RequestParam(required = true) String password,
            @RequestParam(required = true) String passwordCheck,
            Model model
    ) {

        if (username.isEmpty() || nickname.isEmpty() || password.isEmpty() || passwordCheck.isEmpty()) {
            model.addAttribute("result", false);
            model.addAttribute("reason", "값을 입력해주세요");
            return "signup";
        }

        if (username.length() < 5
                || !isAlphaNumeric(username)
                || nickname.length() < 5
                || !isAlphaNumeric(nickname)
        ) {
            model.addAttribute("result", false);
            model.addAttribute("reason", "ID는 영문 숫자 혼합하여 5글자 이상이어햐 합니다.");
            return "signup";
        }

        if (password.length() < 8 || !hasLetterAndDigit(password)) {
            model.addAttribute("result", false);
            model.addAttribute("reason", "PW는 영문, 숫자 각각 최소 한글자 포함 총 8글자 이상이어야 합니다.");
            return "signup";
        }

        if (!password.equals(passwordCheck)) {
            model.addAttribute("result", false);
            model.addAttribute("reason", "비밀번호가 일치가 하지 않습니다.");
            return "signup";
        }

        Member member = memberRepository.findByUsername(username);
        if (member != null) {
            model.addAttribute("result", false);
            model.addAttribute("reason", "이미 존재하는 ID입니다.");
            return "signup";
        }

        Member newMember = new Member(
                username,
                nickname,
                password
        );

        memberRepository.save(newMember);
        log.info("회원가입 성공!");
        return "login";

    }

    // Helper method to check if a string contains only alphanumeric characters
    private boolean isAlphaNumeric(String input) {
        return input.matches("^[a-zA-Z0-9]+$");
    }

    // Helper method to check if a string contains at least one letter and one digit
    private boolean hasLetterAndDigit(String input) {
        return input.matches(".*[a-zA-Z].*") && input.matches(".*\\d.*");
    }

}
