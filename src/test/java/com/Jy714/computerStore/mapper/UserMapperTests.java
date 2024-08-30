package com.Jy714.computerStore.mapper;

import com.Jy714.computerStore.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
// 自己定义test的话必须写上@RunWith(SpringRunner.class)
//表示启动这个单元测试类(单元测试类是不能够运行的), 需要传递一个参数, 必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    /**
     *单元测试方法: 就可以单独独立运行, 不用启动整个项目, 可以做单元测试, 提升了代码的测试效率
     * 1. 必须被 @Test 注解修饰
     * 2. 返回值的类型必须是 void
     * 3. 方法的参数列表不指定任何类型 (不能传参 即使是普通参数 也会导致到不能正常运行)
     * 4. 方法的访问修饰符必须是public
     */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");

        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findUser(){
        User user = userMapper.findByUsername("test");
        System.out.println(user);
    }

    @Test
    public void findUid(){
        System.out.println(userMapper.findByUid(33));
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(33,"123456","管理员",new Date());
    }

    @Test
    public void updateInfo(){
        User user = new User();
        user.setUid(33);
        user.setPhone("15511110000");
        user.setEmail("test2@gmail.com");
        user.setGender(0);
        user.setModifiedUser("管理员");
        user.setModifiedTime(new Date());
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(34,"abc","管理员",new Date());
    }
}
