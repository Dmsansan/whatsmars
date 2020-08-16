package org.hongxi.whatsmars.boot.sample.datasource.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hongxi.whatsmars.boot.sample.datasource.model.User;

import java.util.List;

/**
 * Created by shenhongxi on 2017/6/26.
 */
@Mapper
public interface UserMapper {

    void createIfNotExistsTable();

    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    void insert(User user);

    void insertBatch(List<User> users);

    List<User> query();

    void update(User user);

    void delete(Long id);

    List<User> findByNicknameAndGender(@Param("nickname") String nickname, @Param("gender") Integer gender);

}
