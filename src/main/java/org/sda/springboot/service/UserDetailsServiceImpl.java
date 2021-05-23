package org.sda.springboot.service;

import org.sda.springboot.entities.UserEntity;
import org.sda.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(s);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),userEntity.getPassword(),grantedAuthorityList);
    }
}
