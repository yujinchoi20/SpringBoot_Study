package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //spring test
@Transactional //test가 끝나면 rollback!! DB의 실제 데이터에 저장되지 않음
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
//    @Commit
    void 회원가입() { //join -> Test코드는 한글로 이름을 지어도 됨.
        //given
        Member member = new Member();
        member.setName("spring"); //clear 해줬기에 spring 가입 가능

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        RuntimeException e = assertThrows(RuntimeException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("java.lang.IllegalAccessException: 이미 존재하는 회원입니다.");

        /*try {
            memberService.join(member2);
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("java.lang.IllegalAccessException: 이미 존재하는 회원입니다.");
        }*/

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}