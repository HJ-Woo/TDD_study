package com.library.study.demo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MemberTest {

    @Test
    void test(){
        Member member = new Member("aqq", "aaqq123", "이용자");
        assertThat(member.id,is("aqq"));
        assertThat(member.password,is("aaqq123"));
        assertThat(member.name,is("이용자"));
    }
}
