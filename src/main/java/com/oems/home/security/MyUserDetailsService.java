package com.oems.home.security;

import com.oems.home.dao.LoginJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.singleton;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    LoginJdbcDao loginJdbcDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return new User("Foo","Foo",new ArrayList<>());
        String s=loginJdbcDao.searchPasswordByUsername(username);
        System.out.println(findRoleByUsername(username));
//        return new User(loginJdbcDao.searchUserByUsername(username),loginJdbcDao.searchPasswordByUsername(username),new ArrayList<>());
       return new User(loginJdbcDao.searchUserByUsername(username),loginJdbcDao.searchPasswordByUsername(username),findRoleByUsername(username));
    }

    private Collection<? extends GrantedAuthority> findRoleByUsername(String username) {
        return singleton(new SimpleGrantedAuthority(loginJdbcDao.findRoleByUsername(username)));
    }

}
