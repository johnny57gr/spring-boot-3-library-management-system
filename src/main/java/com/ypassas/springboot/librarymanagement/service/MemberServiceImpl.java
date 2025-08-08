package com.ypassas.springboot.librarymanagement.service;

import com.ypassas.springboot.librarymanagement.model.Member;
import com.ypassas.springboot.librarymanagement.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository theMemberRepository) {
        memberRepository = theMemberRepository;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(int theId) {
        Optional<Member> result = memberRepository.findById(theId);

        Member theMember = null;

        if (result.isPresent()) {
            theMember = result.get();
        }
        else {
            throw new RuntimeException("Did not find member id - " + theId);
        }

        return theMember;
    }

    @Override
    public Member save(Member theMember) {

        boolean isNew = (theMember.getId() == null);

        // Check if email exists
        Optional<Member> byEmail = memberRepository.findByEmail(theMember.getEmail());
        if (byEmail.isPresent() && (isNew || byEmail.get().getId() != theMember.getId())) {
            throw new IllegalArgumentException("Email already exists!");
        }

        // Check if phone exists
        Optional<Member> byPhone = memberRepository.findByPhone(theMember.getPhone());
        if (byPhone.isPresent() && (isNew || byPhone.get().getId() != theMember.getId())) {
            throw new IllegalArgumentException("Phone number already exists!");
        }

        return memberRepository.save(theMember);
    }

    @Override
    public void deleteById(int theId) {
        memberRepository.deleteById(theId);
    }
}
