package com.example.hellospring2.Repository;

import com.example.hellospring2.Domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){ //clear
        repository.clearStore();
    }
    //afterEach 메서드가 없으면 새로 생성한 객체들이 쌓이면서 중복이 일어날 수 밖에 없음
    //트랜잭션 단위로 작업을 할 때, 하나의 트랜잭션이 끝나면 afterEach 메서드를 실행시켜서 저장소를 clear해준다.

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(result).isEqualTo(member);
        //result 객체와 member 객체를 비교하여 같으면 success, 다르면 fail 이 뜬다.
    }

//    @Test
//    public void findById() {
//        //given
//        //when
//        //then
//    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();

        //then
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}