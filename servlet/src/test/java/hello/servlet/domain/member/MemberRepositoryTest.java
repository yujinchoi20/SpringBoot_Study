package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore(); //테스트 끝나면 초기화
    }

    @Test
    void save() {
        //Given
        Member member = new Member("choi", 23);

        //When
        Member saveMember = memberRepository.save(member);

        //Then
        Member findMember = memberRepository.findById(saveMember.getId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        //Given
        Member member1 = new Member("member1", 21);
        Member member2 = new Member("member2", 22);
        
        memberRepository.save(member1);
        memberRepository.save(member2);
        
        //When
        List<Member> result = memberRepository.findAll();

        //Then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}