package com.example.demo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SecurityConfigTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 비밀번호_인코딩Test(){
        String samplePasswd = "1234";
        String encodedPasswd = passwordEncoder.encode(samplePasswd);

        System.out.println("encoded password = "+encodedPasswd);

        boolean matchResult = passwordEncoder.matches(samplePasswd,encodedPasswd);

        assertThat(matchResult).isTrue();

    }
}