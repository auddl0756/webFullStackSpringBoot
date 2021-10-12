package com.example.demo.config;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class Member {
    @Id
    private String email;

    private String password;
    private String name;

    @Enumerated(value = EnumType.STRING)
    private MemberRole memberRole;

    @Builder
    public Member(String email, String name,String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void setMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }
}
