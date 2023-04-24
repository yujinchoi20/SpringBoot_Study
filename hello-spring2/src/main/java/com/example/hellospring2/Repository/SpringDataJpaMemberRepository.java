package com.example.hellospring2.Repository;

import com.example.hellospring2.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //interface : 다중 상속 가능

    Optional<Member> findByName(String name);
}
