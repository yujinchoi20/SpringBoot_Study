package com.example.hellospring2.Service;

import com.example.hellospring2.Domain.Member;
import com.example.hellospring2.Repository.MemberRepository;
import com.example.hellospring2.Repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    //회원 서비스가 메모리 회원 리포지토리를 직접 생성하게 했다. (1)
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //회원 서비스 코드를 DI(Dependency Injection) 가능하게 변경한다.
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //이렇게 생성자에 @Autowired 를 사용하면 객체 생성 시점에 스프링 컨테이너에 해당 스프링 빈을 찾아서 주입해준다.
    //생성자가 1개만 있으면 @Autowired 생략 가능

    //회원 가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //회원 중복 체크
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
