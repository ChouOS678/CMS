package com.cms.mapper;

import com.cms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(User user);

    User selectById(Long id);

    User selectByUsername(String username);

    List<User> selectAll();

    int update(User user);

    int updatePassword(@Param("id") Long id, @Param("password") String password);
}
