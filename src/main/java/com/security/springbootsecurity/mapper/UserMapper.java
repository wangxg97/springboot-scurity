package com.security.springbootsecurity.mapper;

import com.security.springbootsecurity.model.UserDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from t_user where username=#{username}")
    public UserDto findUserByUsername(@Param("username") String username);
}
