package com.nikitkasss.store;

import com.nikitkasss.store.model.AbstractUser;
import com.nikitkasss.store.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserRepository repository;

    public WebSecurityConfig(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/css/**", "/img/**", "/fonts/**", "/scripts/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        Iterable<AbstractUser> list = repository.findAll();

        for(var item: list){
            UserDetails user = User.withDefaultPasswordEncoder()
                    .username(item.getUserName())
                    .password(item.getUserPassword())
                    .roles(item.getUserRole().getValue())
                    .build();
            manager.createUser(user);
        }

        return manager;
    }
}