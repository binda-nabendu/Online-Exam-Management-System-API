package com.oems.home.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityManager extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select nid, password, adminApproval from baseUser where nid=?")
                .authoritiesByUsernameQuery("select nid, role from baseUser where nid=?")
                ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/teacher-board/**").access("hasAuthority('TEACHER')")
                .antMatchers("/admin-board/**").access("hasAuthority('ADMIN')")
                .antMatchers("/student-board/**").access("hasAuthority('STUDENT')")

                .antMatchers("/teachers/all-teachers").authenticated()

                .antMatchers("/courses/add-courses/**").access("hasAuthority('ADMIN')")
                .antMatchers("/all-courses").access("hasAuthority('TEACHER') OR hasAuthority('ADMIN')")
                .antMatchers("/courses/**").access("hasAuthority('TEACHER') OR hasAuthority('ADMIN')")

                .antMatchers("/departmental-course/**").access("hasAuthority('STUDENT')")

                .antMatchers("/exams/questions/**").access("hasAuthority('TEACHER') OR hasAuthority('ADMIN')")
                .antMatchers("/exams/all-exams/**").access("hasAuthority('TEACHER') OR hasAuthority('ADMIN')")
                .antMatchers("/exams/all-result/**").access("hasAuthority('TEACHER') OR hasAuthority('ADMIN')")
                .antMatchers("/exams/upcoming/**").access("hasAuthority('STUDENT')")
                .antMatchers("/exams/previous/**").access("hasAuthority('STUDENT')")

                .antMatchers("/exams/receive-review/**").access("hasAuthority('TEACHER') OR hasAuthority('ADMIN')")
                .antMatchers("/exams/send-review/**").access("hasAuthority('STUDENT')")

                .antMatchers("/teachers/approve-teachers/**").access("hasAuthority('ADMIN')")
                .antMatchers("/students/approve-student/**").access("hasAuthority('ADMIN')")

                .antMatchers("/departmental-course/**").access("hasAuthority('STUDENT')")

                .antMatchers("/courses/add-department/**").access("hasAuthority('ADMIN')")

                .antMatchers("/teachers/add-teacher/**").permitAll()
                .antMatchers("/student/add-student/**").permitAll()

                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }
}
