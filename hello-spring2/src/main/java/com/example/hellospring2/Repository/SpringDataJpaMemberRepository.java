package com.example.hellospring2.Repository;

import com.example.hellospring2.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //interface : 다중 상속 가능

    Optional<Member> findByName(String name);
    //스프링 데이터 JPA는 findByName() 처럼 메서드 이름 만으로 조회 기능을 제공한다.
}
