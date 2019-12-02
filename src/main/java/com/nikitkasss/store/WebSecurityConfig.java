package com.nikitkasss.store;

import com.nikitkasss.store.dto.user.BuyerInfoDto;
import com.nikitkasss.store.model.AbstractUser;
import com.nikitkasss.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManagerBuilder auth;

    @Autowired
    private UserRepository repository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                    .antMatchers("/all/", "/all/login/", "/all/registration", "/css/**", "/fonts/**", "/img/**", "/scripts/**")
                    .permitAll()
                    .antMatchers("/admin/**").access("hasRole('ADMIN')")
                    .antMatchers("/seller/**").access("hasRole('SELLER') or hasRole('ADMIN')")
                    .antMatchers("/all/**").access( "hasRole('SELLER') or hasRole('ADMIN')")
//                    .antMatchers("/all/**").authenticated()

//                .anyRequest()
//                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/all/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select user_username, user_password, true from abstract_user where user_username=?")
                .authoritiesByUsernameQuery("select user_username, user_role from abstract_user where user_username=?");
    }
}