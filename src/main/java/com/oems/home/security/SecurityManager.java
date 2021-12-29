package com.oems.home.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityManager extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService getUserDetailsService(){
        return new AllUserDetailsServiceProvider();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(this.getUserDetailsService());

        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final String admin = "ADMIN";
        final String teacher = "TEACHER";
        final String student = "STUDENT";

        http.authorizeRequests()
                .antMatchers("/teacher-board").hasRole(teacher)
                .antMatchers("/admin-board").hasRole(admin)
                .antMatchers("/student-board").hasRole(student)

                .antMatchers("/teachers/all-teachers").hasAnyRole(admin, teacher,student)
                .antMatchers("/teachers/add-teachers").hasRole(admin)
                .antMatchers("/teachers/approve-teachers").hasRole(admin)

                .antMatchers("/courses/add-courses").hasRole(admin)
                .antMatchers("/courses/**").hasAnyRole(admin, teacher)

                .antMatchers("/students/**").hasRole(admin)

                .antMatchers("/departmental-course/**").hasRole(student)

                .antMatchers("/**").permitAll()

                .and().formLogin().and().csrf().disable();
    }
}
