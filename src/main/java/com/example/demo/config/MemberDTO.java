package com.example.demo.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@ToString
@Getter
@Setter
public class MemberDTO extends User {
    private String email;
    private String name;

    public MemberDTO(String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username,password,authorities);
        this.email = username;
    }
}
