package com.library.study.demo.service;

import com.library.study.demo.domain.Admin;
import com.library.study.demo.domain.Member;
import com.library.study.demo.domain.User;
import com.library.study.demo.repository.MemberRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Getter
public class MemberService implements UserService{

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findById(String id) {
        return memberRepository.findMemberById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다."));
    }

    @Override
    public User join(User user) {
        Member member = (Member) user;
        return memberRepository.save(member);
    }


}
