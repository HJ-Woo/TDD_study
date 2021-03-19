package com.library.study.demo.domain;

import com.library.study.demo.controller.JoinDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Admin extends User{

    public Admin(String id, String password, String name) {
        super(id, password, name);
    }

    public Admin(JoinDTO joinDTO) {
        super(joinDTO.getId(), joinDTO.getPassword(), joinDTO.getName());
    }
}
