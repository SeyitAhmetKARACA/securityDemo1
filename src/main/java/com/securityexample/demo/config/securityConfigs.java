package com.securityexample.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class securityConfigs extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(a ->
                        a.mvcMatchers(HttpMethod.GET,"/api","/api/**").hasAnyAuthority("READ")
                .anyRequest().hasAnyAuthority("WRITE"))
                .httpBasic();

        http.csrf().disable();
    }

    @Bean
    public UserDetailsService users(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);/*{
          @Override
          protected List<GrantedAuthority> loadUserAuthorities(String  username){
              return AuthorityUtils.createAuthorityList("ROLE_USER");
          }
        };*/
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    UserDetailsService userDetailsService(){
//        return new InMemoryUserDetailsManager(
//                User.withDefaultPasswordEncoder()
//                        .username("seyit")
//                        .password("1")
//                        .roles("USER")
//                        .build()
//        );
//    }

}
