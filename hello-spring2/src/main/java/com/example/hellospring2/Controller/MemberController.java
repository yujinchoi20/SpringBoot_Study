package com.example.hellospring2.Controller;

import com.example.hellospring2.Domain.Member;
import com.example.hellospring2.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    //이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입이라고 한다.

    //Consider defining a bean of type 'hello.hellospring.service.MemberService' in your configuration.
    //memberService가 스프링 빈으로 등록되어 있지 않기 때문에 위와 같은 오류가 발생한다.

    //@Component 어노테이션이 있으면 스프링 빈으로 자동 등록된다.
    //@Controller 가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문
    //@Controller 에 @Component 가 내장되어 있다. 이외에도 @Service, @Repository 에도 내장되어 있다.

    //스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다.
    //따라서 같은 스프링 빈이면 모두 같은 인스턴스다. 기본적으로 싱글톤으로 설정된다.

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //회원 등록 후 홈 화면으로 돌아감
    }

    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "/members/memberList";
    }
}
