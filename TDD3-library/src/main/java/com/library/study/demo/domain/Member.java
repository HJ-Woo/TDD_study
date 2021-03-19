package com.library.study.demo.domain;

import com.library.study.demo.controller.JoinDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@NoArgsConstructor
@Setter
@Entity
public class Member extends User {

    public Member(String id, String password, String name){
        super(id, password, name);
    }

    public Member(JoinDTO joinDTO) {
        super(joinDTO.getId(), joinDTO.getPassword(), joinDTO.getName());
    }
}
