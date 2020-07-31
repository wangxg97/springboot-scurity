package com.security.springbootsecurity.service;

import com.security.springbootsecurity.mapper.UserMapper;
import com.security.springbootsecurity.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//springboot security  UserDetailsService 中 loadUserByUsername 根据账号查询用户信息
@Service
@Slf4j
public class SpringDataUserDatailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userMapper.findUserByUsername(username);
        if (null ==user){
            //return null 即可，不用抛出错误，用户不存在的错误由上层（DaoAuthenticationProvider）统一处理
            return  null;
        }
        UserDetails userDetails = User.withUsername(user.getUsername()).password(user.getPassword()).authorities("p1", "p2").build();
        return userDetails;
    }
}
