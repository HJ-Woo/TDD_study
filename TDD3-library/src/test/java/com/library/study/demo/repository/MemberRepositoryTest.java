package com.library.study.demo.repository;

import com.library.study.demo.domain.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    private Member member;

    @BeforeAll
    public void setUp() {
        member = new Member("qwe13","sssp11","haii");
    }

    @Test
    public void memberSaveTest() {
        Member saveMember = memberRepository.save(member);
        assertThat(saveMember.getName(),is(member.getName()));
        assertThat(saveMember.getPassword(),is(member.getPassword()));
        assertThat(saveMember.getId(),is(member.getId()));
    }

    @Test
    public void memberUpdateTest() {
        Member updateMember = memberRepository.save(member);
        String changePassword = "qqw112";
        member.setPassword(changePassword);
        Member findMember = memberRepository.findById(updateMember.getId()).orElse(null);
        assertThat(findMember.getPassword(), is(updateMember.getPassword()));
    }

    @Test
    public void memberDeleteTest() {
        Member deleteMember = memberRepository.save(member);
        memberRepository.deleteById(deleteMember.getId());
        List<Member> memberList = memberRepository.findAll();

        assertThat(memberList.size(), is(0));
    }

}
