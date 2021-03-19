package com.library.study.demo.repository;

import com.library.study.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional <Member> findMemberById(String id);
}
