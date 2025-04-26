package com.bookclub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/about", "/contact", "/login", "/css/**").permitAll() // Permit these URLs
                                                                                                  // without
                                                                                                  // authentication
                .antMatchers("/wishlist/**").hasRole("USER") // Protect wishlist with USER role
                .antMatchers("/admin/**").hasRole("ADMIN") // Protect admin paths with ADMIN role
                .anyRequest().authenticated() // All other requests must be authenticated
                .and()
                .formLogin()
                .loginPage("/login") // Custom login page
                .permitAll() // Allow everyone to access the login page
                .defaultSuccessUrl("/home", true) // Redirect to home page on successful login
                .and()
                .logout()
                .permitAll(); // Allow everyone to logout
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // In-memory authentication setup for testing purposes
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}password") // {noop} means no password encoder is used (for simplicity)
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}adminpass")
                .roles("ADMIN", "USER")
                .build();

        auth.inMemoryAuthentication()
                .withUser(user)
                .withUser(admin);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }
}
