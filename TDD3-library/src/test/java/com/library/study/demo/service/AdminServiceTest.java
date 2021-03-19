package com.library.study.demo.service;

import com.library.study.demo.domain.Admin;
import com.library.study.demo.domain.User;
import com.library.study.demo.repository.AdminRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdminServiceTest {
    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    private Admin admin;

    @BeforeAll
    public void setUp() {
        admin = new Admin("id", "pwd", "name");
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

    }

    @Test
    public void joinAdminTest() {
        Admin joinAdmin = (Admin) adminService.join(admin);
        assertThat(joinAdmin.getPassword(), is(admin.getPassword()));
        assertThat(joinAdmin.getId(), is(admin.getId()));
        assertThat(joinAdmin.getName(), is(admin.getName()));

    }


}
