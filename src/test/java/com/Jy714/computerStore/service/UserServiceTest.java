package com.Jy714.computerStore.service;

import com.Jy714.computerStore.entity.User;
import com.Jy714.computerStore.entity.VO.UserVO;
import com.Jy714.computerStore.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
// 自己定义test的话必须写上@RunWith(SpringRunner.class)
//表示启动这个单元测试类(单元测试类是不能够运行的), 需要传递一个参数, 必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void reg(){
       try {
           User user = new User();
           user.setUsername("tom");
           user.setPassword("123456");
           userService.reg(user);
           System.out.println("Okay");
       }catch (ServiceException e){
           // 获取类的对象 在获取累的名称
           System.out.println(e.getClass().getSimpleName());
           // 获取异常的具体描述信息
           System.out.println(e.getMessage());
       }
    }

    @Test
    public void updatePassword(){
        userService.changePassword(33,"管理员","654321","123456");
    }

    @Test
    public void getByUid(){
        UserVO user = userService.getByUid(33);
        System.out.println(user);
    }

    @Test
    public void changInfo(){
        User user = new User();
        user.setUid(36);
        user.setPhone("123467890");
        user.setEmail("test03@gmail.com");
        user.setGender(1);

        userService.changeInfo(user);
        System.out.println("OK");
    }
}
