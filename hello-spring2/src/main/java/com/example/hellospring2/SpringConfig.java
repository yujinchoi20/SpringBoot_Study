package com.example.hellospring2;

import com.example.hellospring2.Repository.MemberRepository;
import com.example.hellospring2.Repository.MemoryMemberRepository;
import com.example.hellospring2.Service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
