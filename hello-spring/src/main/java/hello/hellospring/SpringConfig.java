package hello.hellospring;

import hello.hellospring.Service.MemberService;
import hello.hellospring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /*private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository(); //Memory
//        return new jdbcMemberRepository(dataSource); //jdbc
//        return new jdbcTemplateMemberRepository(dataSource); //jdbcTemplate
        return new jpaMemberRepository(em);
    }
}
