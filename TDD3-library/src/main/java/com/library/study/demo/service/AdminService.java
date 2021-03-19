package com.library.study.demo.service;

import com.library.study.demo.domain.Admin;
import com.library.study.demo.domain.Book;
import com.library.study.demo.domain.Library;
import com.library.study.demo.domain.User;
import com.library.study.demo.repository.AdminRepository;
import com.library.study.demo.repository.BookRepository;
import com.library.study.demo.repository.LibraryRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Getter
public class AdminService implements UserService{

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public User join(User user) {
        Admin admin = (Admin) user;
        return adminRepository.save(admin);
    }

    public Admin findById(String id){
        return adminRepository.findAdminById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다."));
    }

    /*public Admin findOne(String id) {
        Admin borrower = adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 사용자입니다."));
        return borrower;
    }*/

}
