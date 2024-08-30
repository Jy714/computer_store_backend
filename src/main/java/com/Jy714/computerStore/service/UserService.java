package com.Jy714.computerStore.service;

import com.Jy714.computerStore.entity.DTO.UserDTO;
import com.Jy714.computerStore.entity.User;
import com.Jy714.computerStore.entity.VO.UserVO;


/** 用户模块业务层接口 */
public interface UserService {
    /**
     * 用户注册方法
     * @param user
     */
    void reg(User user);

    /**
     * 用户登录功能
     * @param userDTO
     * @return
     */
    String login(UserDTO userDTO);

    /**
     * 用户修改密码功能
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 根据用户id查询用户的数据
     * @param uid
     * @return
     */
    UserVO getByUid(Integer uid);

    /**
     * 更新用户的数据操作
     * @param user
     */
    void changeInfo(User user);

    /**
     * 修改用户的头像
     * @param uid
     * @param avatar
     * @param username
     */
    void changeAvatar(Integer uid,String avatar,String username);
}
