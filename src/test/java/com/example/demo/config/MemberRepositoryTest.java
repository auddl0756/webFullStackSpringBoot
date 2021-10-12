package com.example.demo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@naver.com")
                    .name("name" + i)
                    .password(passwordEncoder.encode(new String("1234")))
                    .build();

            if (i == 10) {
                member.setMemberRole(MemberRole.ADMIN);
            } else {
                member.setMemberRole(MemberRole.USER);
            }

            memberRepository.save(member);
        });
    }

    @Test
    public void 이메일로_회원_조회Test(){
        Optional<Member> optionalMember = memberRepository.findByEmail("user1@naver.com");
        Member member = optionalMember.get();

        System.out.println(member);
    }
}