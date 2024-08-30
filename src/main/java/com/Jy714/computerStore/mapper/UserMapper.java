package com.Jy714.computerStore.mapper;

import com.Jy714.computerStore.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;


/** 用户模块的持久层接口 */
@Mapper
public interface UserMapper {

    /**
     * 插入用户的数据
     * @param user
     * @return 受影响的行数 (增删改 都影响行数作为返回值, 可以根据返回值来判断是否执行成功)
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户id来修改用户密码
     * @param uid
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户id查询用户的数据
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据信息
     * @param user
     * @return
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据用户uid值来修改用户的头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(Integer uid, String avatar, String modifiedUser, Date modifiedTime);
}
