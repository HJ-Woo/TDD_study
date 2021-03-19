package com.library.study.demo.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerApiTest {
    @Autowired
    private TestRestTemplate testTemplate;

    private String uri = "/join";
    private JoinDTO joinDTO;

    @BeforeAll
    void setUp(){
        joinDTO = new JoinDTO();
        joinDTO.setId("id");
        joinDTO.setPassword("pwd");
        joinDTO.setName("name");
        joinDTO.setAdmin(true);
    }

    @Test
    void joinRest() {
        ResponseEntity<String> responseEntity = testTemplate.postForEntity(uri, joinDTO, String.class);
        assertThat(responseEntity.getBody(), is(containsString("회원가입 성공")));
    }


}
