package com.ggw.travel.dao;

import com.ggw.travel.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO t_user VALUES(#{id}, #{username}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addUser(User user);

    @Select("SELECT * FROM t_user WHERE username=#{username}")
    User getByUsername(String username);

}
