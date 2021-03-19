package com.library.study.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.study.demo.domain.User;
import com.library.study.demo.service.AdminService;
import com.library.study.demo.service.MemberService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private AdminService adminService;

    @Mock
    private MemberService memberService;

    private MockMvc mockMvc;

    @BeforeAll
    public void setUp() {
        when(adminService.join(any(User.class))).thenReturn(null);
        when(memberService.join(any(User.class))).thenReturn(null);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    }

    @SneakyThrows
    @Test()
    void joinTest() throws Exception {
        String uri = "/join";
        JoinDTO joinDTO = new JoinDTO();
        joinDTO.setId("id");
        joinDTO.setPassword("pwd");
        joinDTO.setName("name");
        joinDTO.setAdmin(true);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(joinDTO);

        MvcResult mvcResult = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

    }


}
