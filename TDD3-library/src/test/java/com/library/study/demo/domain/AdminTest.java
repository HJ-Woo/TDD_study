package com.library.study.demo.domain;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdminTest {

    @Test
    public void adminConstruct(){
        Admin admin = new Admin("eee", "eeww123", "관리자");
        assertThat(admin.id,is("eee"));
        assertThat(admin.password,is("eeww123"));
        assertThat(admin.name,is("관리자"));

    }
}
