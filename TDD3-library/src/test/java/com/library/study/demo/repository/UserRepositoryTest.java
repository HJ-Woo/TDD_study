package com.library.study.demo.repository;

import com.library.study.demo.domain.Admin;
import com.library.study.demo.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private Member member;
    private Admin admin;

    @BeforeEach
    void setUp(){
        member = new Member("memberId","memberPwd", "memberName");
        admin = new Admin("adminId", "adminPwd", "adminName");
    }

    @Test
    public void UserSaveTest() {
        Member saveMember = userRepository.save(member);
        Admin saveAdmin = userRepository.save(admin);

        assertThat(saveMember.getId(), is(member.getId()));
        assertThat(saveAdmin.getId(), is(admin.getId()));
    }
}
