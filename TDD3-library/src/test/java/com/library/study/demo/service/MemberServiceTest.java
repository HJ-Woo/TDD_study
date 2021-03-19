package com.library.study.demo.service;


import com.library.study.demo.domain.Member;
import com.library.study.demo.repository.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member member;

    @BeforeAll
    public void setUp() {
        member = new Member("id", "pwd", "name");
        when(memberRepository.save(any(Member.class))).thenReturn(member);
    }

    @Test
    public void joinMemberTest() {
        Member joinMember = (Member) memberService.join(member);
        assertThat(joinMember.getPassword(), is(member.getPassword()));
        assertThat(joinMember.getId(), is(member.getId()));
        assertThat(joinMember.getName(), is(member.getName()));

    }
}
