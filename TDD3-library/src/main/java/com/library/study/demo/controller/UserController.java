package com.library.study.demo.controller;


import com.library.study.demo.domain.Admin;
import com.library.study.demo.domain.Member;
import com.library.study.demo.service.AdminService;
import com.library.study.demo.service.MemberService;
import com.library.study.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
public class UserController {

    public final AdminService adminService;
    public final MemberService memberService;
    public final static String joinMessage = "회원가입 성공";

    public UserController(AdminService adminService, MemberService memberService) {
        this.adminService = adminService;
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody JoinDTO joinDTO){
        String message = "";
        if(joinDTO.isAdmin()){
            adminService.join(new Admin(joinDTO));
            message += "Admin";
        } else{
            memberService.join(new Member(joinDTO));
            message += "Member";
        }

        return ResponseEntity.ok(message+joinMessage);
    }
}
