package com.Jy714.computerStore.service.serviceImpl;

import com.Jy714.computerStore.entity.DTO.UserDTO;
import com.Jy714.computerStore.entity.User;
import com.Jy714.computerStore.entity.VO.UserVO;
import com.Jy714.computerStore.mapper.UserMapper;
import com.Jy714.computerStore.service.UserService;
import com.Jy714.computerStore.service.ex.*;
import com.Jy714.computerStore.utils.Jwt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**用户模块业务层实现类*/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Jwt jwt;

    @Override
    public void reg(User user) {
        // 调用 findByUsername(String username) 判断用户是否被注册过
        User result = userMapper.findByUsername(user.getUsername());
        //判断结果集是否为null
        //如果不为null则抛出用户名以被占用的异常
        if(result != null){
            //抛出异常
            throw new UsernameDuplicatedException("用户名已被占用");
        }
        // 补全数据: is_delete设置成 0 == 没有删除
        user.setIsDelete(0);
        // 补全数据: 4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        // 密码加密处理的实现： MD5 算法
        // (串 + password + 串) --- md5算法进行加密, 连续加载3次
        // 盐值 + password + 盐值 --- 盐值就是一个随机的字符串
        String oldPassword = user.getPassword();
        //获取盐值(随机生成一个盐值)
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全数据: 盐值的记录
        user.setSalt(salt);
        // 将密码和盐值作为一个整体进行加密处理, 忽略原有的密码强度提升了数据的安全性
        String md5Password = getMD5Password(oldPassword, salt);
        //将加密之后的密码重新补全设置到user对象中，
        user.setPassword(md5Password);
        // 执行注册业务功能的实现(成功: rows == 1)
        Integer rows = userMapper.insert(user);
        if(rows != 1){
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public String login(UserDTO userDTO) {
        //根据用户名称来查询用户的诗句是否存在 如果不在啧抛出异常
        User result = userMapper.findByUsername(userDTO.getUsername());
        if(result == null){
            throw new UserNotFoundException("用户数据不存在");
        }
        // 判断is_delete字段的值是否为1 表示被标记为删除
        if(result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //检测用户的密码是否匹配
        // 1. 先获取到数据库中加密之后的密码
        String oldPassword = result.getPassword();
        // 2. 和用户传递过来的密码进行比较
        // 2.1 现货去盐值: 上一次在注册时所自动生成的盐值
        String salt = result.getSalt();
        // 2.2 将用户的密码按照相同的md5算法的规则进行加密
        String md5Password = getMD5Password(userDTO.getPassword(), salt);
        // 3. 将密码进行比较
        if(!md5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        //将用户数据返回, 返回的数据是为了辅助其他页面做数据展示使用(uid, username, avatar)
        /**
         * 直接把用户信息存储到jwt里 让前端解析拿到数据
         */
//        UserVO user = new UserVO();
//        BeanUtils.copyProperties(result,user);

        //为用户添加token
        //1. 设置好payload
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("uid",result.getUid());
        claims.put("username",result.getUsername());
        claims.put("avatar",result.getAvatar());
        //2. 创建token
        String token = jwt.genJwt(claims);
        return token;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        // 原始密码和数据库中的密码进行比较
        // 1.1 获取数据库中salt的值
        String salt = result.getSalt();
        // 1.2 使用md5来进行加密
        String oldMd5Password = getMD5Password(oldPassword,salt);
        // 2. 原始密码和数据库密码进行比较
        if(!oldMd5Password.equals(result.getPassword())){
            throw new PasswordNotMatchException("密码错误");
        }
        // 对新密码进行加密
        String newMd5Password = getMD5Password(newPassword,salt);
        // 插入新密码 和 其他数据补全
        Integer rows = userMapper.updatePasswordByUid(result.getUid(), newMd5Password, username, new Date());
        // 查看是否修改成功
        if(rows != 1){
            throw new InsertException("更新数据产生未知的异常");
        }
    }

    @Override
    public UserVO getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(result,userVO);
        return userVO;
    }

    /**
     * User对象中的数据 phone /email/gender, 手动将uid/ username封装 user对象中
     * @param user
     */
    @Override
    public void changeInfo(User user) {
        User result = userMapper.findByUid(user.getUid());
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //补全数据
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        //执行更新数据
        Integer rows = userMapper.updateInfoByUid(user);
        if(rows != 1){
            throw new UpdateException("更新数据时产生未知异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        // 查询当前的用户数据是否存在
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }

        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());

        if(rows != 1){
            throw new UpdateException("更新用户头像产生未知的异常");
        }
    }



    /**
     * 定义一个md5算法的加密处理
     */
    private String getMD5Password(String password,String salt){
        for (int i = 0 ; i < 3; i++){
            // md5加密算法方法调用 (循环3次)
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        //返回加密之后的密码
        return password;
    }
}
