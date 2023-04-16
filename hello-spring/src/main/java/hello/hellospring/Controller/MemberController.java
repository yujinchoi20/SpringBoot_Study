package hello.hellospring.Controller;

import hello.hellospring.Service.MemberService;
import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    //생성자를 통한 주입을 주로 사용한다.
    //의존관계가 실행중에 동적으로 변하는 경우가 없기 때문에 생성자 주입을 사용한다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value="/members/new")
    public String createForm(){
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //회원가입이 끝나면 홈 화면으로
    }

    @GetMapping(value="/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "/members/memberList";
    }
}
