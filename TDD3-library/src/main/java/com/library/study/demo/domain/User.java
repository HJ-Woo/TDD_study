package com.library.study.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public abstract class User {
    @Id
    public String id;
    public String password;
    public String name;

    protected User(String id, String password, String name){
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
