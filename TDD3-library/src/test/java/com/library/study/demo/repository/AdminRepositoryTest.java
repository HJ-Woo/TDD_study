package com.library.study.demo.repository;

import com.library.study.demo.domain.Admin;
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
public class AdminRepositoryTest {
    @Autowired
    private AdminRepository adminRepository;
    private Admin admin;

    @BeforeAll
    void setUp(){
        admin = new Admin("아이디바꿈", "이름", "pwd");

    }

    @Test
    void adminSaveTest(){
        adminRepository.save(admin);
        Admin findAdmin = adminRepository.findById("아이디바꿈").orElse(null);
        assertThat(findAdmin.getName(), is(admin.getName()));
    }

    @Test
    void adminUpdateTest() {
        Admin updateAdmin = adminRepository.save(admin);
        updateAdmin.setPassword("qwe123");
        Admin findAdmin = adminRepository.findById(updateAdmin.getId()).orElse(null);
        assertThat(findAdmin.getPassword(), is(updateAdmin.getPassword()));

    }

    @Test
    void adminDeleteTest() {
        Admin deleteAdmin = adminRepository.save(admin);
        adminRepository.deleteById(deleteAdmin.getId());
        List<Admin> adminlist = adminRepository.findAll();
        assertThat(adminlist.size(), is(0));
    }




}
