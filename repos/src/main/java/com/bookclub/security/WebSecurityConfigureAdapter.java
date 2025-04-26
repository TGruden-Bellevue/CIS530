package com.bookclub.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigureAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/about", "/contact", "/login", "/css/**").permitAll() // Public pages
                .antMatchers("/wishlist/**").hasRole("USER") // Only accessible by users with "USER" role
                .antMatchers("/admin/**").hasRole("ADMIN") // Only accessible by users with "ADMIN" role
                .anyRequest().authenticated() // All other requests must be authenticated
                .and()
                .formLogin()
                .loginPage("/login") // Custom login page URL
                .permitAll() // Allow all users to access the login page
                .defaultSuccessUrl("/home", true) // Redirect to /home after successful login
                .and()
                .logout()
                .permitAll(); // Allow all users to log out
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // In-memory authentication setup for testing
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admins")
                .roles("ADMIN", "USER")
                .build();

        auth.inMemoryAuthentication()
                .withUser(user)
                .withUser(admin);
    }
}
