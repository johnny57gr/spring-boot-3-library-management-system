package com.ypassas.springboot.librarymanagement.repository;

import com.ypassas.springboot.librarymanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    // add custom method if needed
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPhone(String phone);
}
