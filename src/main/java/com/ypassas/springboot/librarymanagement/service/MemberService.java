package com.ypassas.springboot.librarymanagement.service;

import com.ypassas.springboot.librarymanagement.model.Member;

import java.util.List;

public interface MemberService {

    List<Member> findAll();

    Member findById(int theId);

    Member save(Member theMember);

    void deleteById(int theId);
}
