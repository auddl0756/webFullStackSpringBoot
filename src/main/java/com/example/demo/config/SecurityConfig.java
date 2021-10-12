package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   @Bean
    PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }

   //직접 Authentication Manager를 설정할 수 있도록 해주는 cofigure(매니저빌더) 메서드
    //UserDetailsService를 구현한 클래스를 따로 만들었으면 이 부분은 사용하지 않도록 한다.
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                .password("$2a$10$IkqoXUrjjnxXCASV4ClzS.RT6U3FZVhGyJuerGL0FuBlAvzUpYBUO")
//                .roles("USER");
//    }

    //특정 URL(리소스)에 접근 제한 설정하기
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/reserve").hasRole("USER");

        http.formLogin();
        http.csrf().disable();
        http.logout();

    }
}


// 스프링 시큐리티를 이용할 때는 별도의 시큐리티 설정 클래스를 사용하는 것이 일반적.
// WebSecurityConfigurerAdapter 클래스를 상속하여 oevrride를 통해 여러 시큐리티 관련 설정을 조정.

// 스프링 시큐리티에서 인증(Authentication)과 인가(Authrization)이 핵심 개념이라 한다.
// 인증 : 자신을 증명하는 것
// 인가 : 인증을 통해 받은 정보로 허가/제한을 하는 것(Access control), 사용자의 권한이 적절한지

// 스프링 시큐리티의 구성 객체, 동작 흐름을 이해하는 것이 중요.
// 1. 여러 필터가 동작한다. (filter chain)
// 2. 여러 객체가 정보를 주고받으면서 동작한다.
//      : 필터들 -> Authentication Manager -> Authentication Provider -> UserDetailsService
//      Authentication Manager는 말 그대로 인증을 위한 매니저일 것이다.
//      실제 인증은 UserDetailsService를 통해 이루어진다?
//      실제 동작에서 전달되는 파라미터는 UsernamePasswordAutheticationToken과 같은 토큰이라는 이름으로 전달된다.
//      Authentication Manager는 다양한 인증 처리 방식을 제공해야 한다.
//      Authentication Manager는 Authentication Provider로 처리한다.
//      다양한 Authentication Provider가 있다. strategy 패턴인듯 하다.
//      Authentication Provider는 UserDetailsService를 이용한다.

// ******단계********
// 1. 사용자가 url 입력
// 2. 사용자가 인증하도록 로그인 화면 보여주기
// 3. Authentication Manager가 적절한 Authentication Provider를 찾아서 인증
//    Authentication Provider는 실제로 UserDetailsSerivce를 구현한 객체를 이용해서 처리
// 4. 인가과정에서 문제가 없으면 정상적인 화면을 보여주게 됨.

/********************************* 동작한 filter들 ****************************/
//    Redirecting to 'http://localhost:8080/login'
//        o.s.s.w.header.writers.HstsHeaderWriter  : Not injecting HSTS header since it did not match the requestMatcher org.springframework.security.web.header.writers.HstsHeaderWriter$SecureRequestMatcher@7ece6dd7
//        w.c.HttpSessionSecurityContextRepository : SecurityContext is empty or contents are anonymous - context will not be stored in HttpSession.
//        s.s.w.c.SecurityContextPersistenceFilter : SecurityContextHolder now cleared, as request processing completed
//        o.s.security.web.FilterChainProxy        : /login at position 1 of 15 in additional filter chain; firing Filter: 'WebAsyncManagerIntegrationFilter'
//        o.s.security.web.FilterChainProxy        : /login at position 2 of 15 in additional filter chain; firing Filter: 'SecurityContextPersistenceFilter'
//        w.c.HttpSessionSecurityContextRepository : HttpSession returned null object for SPRING_SECURITY_CONTEXT
//        w.c.HttpSessionSecurityContextRepository : No SecurityContext was available from the HttpSession: org.apache.catalina.session.StandardSessionFacade@6315e571. A new one will be created.
//        o.s.security.web.FilterChainProxy        : /login at position 3 of 15 in additional filter chain; firing Filter: 'HeaderWriterFilter'
//        o.s.security.web.FilterChainProxy        : /login at position 4 of 15 in additional filter chain; firing Filter: 'CsrfFilter'
//        o.s.security.web.FilterChainProxy        : /login at position 5 of 15 in additional filter chain; firing Filter: 'LogoutFilter'
//        o.s.s.w.u.matcher.AntPathRequestMatcher  : Request 'GET /login' doesn't match 'POST /logout'
//        o.s.security.web.FilterChainProxy        : /login at position 6 of 15 in additional filter chain; firing Filter: 'UsernamePasswordAuthenticationFilter'
//        o.s.s.w.u.matcher.AntPathRequestMatcher  : Request 'GET /login' doesn't match 'POST /login'
//        o.s.security.web.FilterChainProxy        : /login at position 7 of 15 in additional filter chain; firing Filter: 'DefaultLoginPageGeneratingFilter'
//        o.s.s.w.header.writers.HstsHeaderWriter  : Not injecting HSTS header since it did not match the requestMatcher org.springframework.security.web.header.writers.HstsHeaderWriter$SecureRequestMatcher@7ece6dd7
//        w.c.HttpSessionSecurityContextRepository : SecurityContext is empty or contents are anonymous - context will not be stored in HttpSession.
//        s.s.w.c.SecurityContextPersistenceFilter : SecurityContextHolder now cleared, as request processing completed
