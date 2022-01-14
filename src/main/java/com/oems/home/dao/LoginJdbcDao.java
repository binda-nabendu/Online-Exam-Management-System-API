package com.oems.home.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Component
@Repository
public class LoginJdbcDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public LoginJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public String searchUserByUsername(String username) {
        String s1= "select nid from baseuser where nid="+username;
        return Optional.ofNullable(jdbcTemplate.queryForObject(s1, String.class)).orElse("UNKNOWN");
    }

    public String searchPasswordByUsername(String username) {
        String s1= "select password from baseuser where nid="+username;
        return Optional.ofNullable(jdbcTemplate.queryForObject(s1, String.class)).orElse("UNKNOWN");
    }


    public String findRoleByUsername(String username) {
        String s1= "select role from baseuser where nid="+username;
        return Optional.ofNullable(jdbcTemplate.queryForObject(s1, String.class)).orElse("UNKNOWN");
    }
}
