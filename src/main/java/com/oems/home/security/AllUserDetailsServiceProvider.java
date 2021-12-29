package com.oems.home.security;

import com.oems.home.model.BaseUser;
import com.oems.home.model.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AllUserDetailsServiceProvider implements UserDetailsService {
    @Autowired
    private UserQuery userQuery;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUser user = userQuery.findByNID(username);
        if(user==null)
            throw new UsernameNotFoundException("User not found");
        return new UserDetailed(user);
    }
}
