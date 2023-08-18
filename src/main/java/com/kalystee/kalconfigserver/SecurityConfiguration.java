package com.kalystee.kalconfigserver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Configuration
@Slf4j
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/encrypt", "/actuator/**", "/favicon.ico", "/api-docs", "/swagger-ui/**").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/**").authenticated().and().httpBasic();

        httpSecurity.csrf().disable();
        httpSecurity.cors().disable();

        return httpSecurity.build();
    }

    private static class HeaderHttpRequestWrapper extends HttpServletRequestWrapper{

        public HeaderHttpRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getHeader(String name) {
            if(HttpHeaders.AUTHORIZATION.equalsIgnoreCase(name)){
                return null;
            }
            return super.getHeader(name);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            if(HttpHeaders.AUTHORIZATION.equalsIgnoreCase(name)){
                return Collections.enumeration(Collections.emptyList());
            }
            return super.getHeaders(name);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> values = Collections.list(super.getHeaderNames());
            values.remove(HttpHeaders.AUTHORIZATION);
            return Collections.enumeration(values);
        }
    }
}
