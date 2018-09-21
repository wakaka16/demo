package com.zhifei.demo.mapper;

import com.zhifei.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    //通用查询sql
    String QSQL = "select user_id userId,name,pwd,logo from t_user ";

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Insert("insert into t_user(name,pwd,logo) values(#{user.name},#{user.pwd},#{user.logo})")
    //注意：如果keyProperty不是user.userId（userId）就获取不了返回的userId
    @Options(useGeneratedKeys = true, keyProperty = "user.userId", keyColumn = "user_id")
    Integer insert(@Param("user") User user);

    /**
     * 查询所有用户
     * @return
     */
    @Select(QSQL)
    List<User> queryAll();

    /**
     * 根据用户名查询用户
     * @param user
     * @return
     */
    @Select(QSQL+"where name=#{user.name}")
    List<User> queryByName(@Param("user") User user);

    /**
     * 根据用户名和密码查询用户
     * @param user
     * @return
     */
    @Select(QSQL+"where name=#{user.name} and pwd=#{user.pwd}")
    User queryByNameAndPwd(@Param("user") User user);

    /**
     * 根据用户id进行查询用户
     * @param userId
     * @return
     */
    @Select(QSQL+"where user_id=#{userId}")
    User queryByUserId(@Param("userId") Long userId);

}
