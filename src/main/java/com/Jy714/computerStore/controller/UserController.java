package com.Jy714.computerStore.controller;

import com.Jy714.computerStore.controller.ex.*;
import com.Jy714.computerStore.entity.DTO.UserChangePassword;
import com.Jy714.computerStore.entity.DTO.UserDTO;
import com.Jy714.computerStore.entity.User;
import com.Jy714.computerStore.entity.VO.UserVO;
import com.Jy714.computerStore.service.UserService;
import com.Jy714.computerStore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController //@Controller + @ResponseBody
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @PostMapping("/reg")
    public Result userRegister(@RequestBody User user){
            userService.reg(user);
            return Result.success(200,"用户注册成功");
    }

    @PostMapping("/login")
    public Result userLogin(@RequestBody UserDTO userDTO){
        String token = userService.login(userDTO);

        return Result.success(200,(Object)token);
    }

    @PutMapping("/change_password")
    public Result changePassword(@RequestBody UserChangePassword userChangePassword){
        userService.changePassword(userChangePassword.getUid(), userChangePassword.getUsername(), userChangePassword.getOldPassword(), userChangePassword.getNewPassword());
        return Result.success(200,"success");
    }

    @GetMapping("/get_by_uid/{uid}")
    public Result getByUid(@PathVariable Integer uid){
        UserVO user = userService.getByUid(uid);
        return Result.success(200,user);
    }

    @PutMapping("/change_info")
    public Result changInfo(@RequestBody User user){
        userService.changeInfo(user);
        return Result.success(200,"success");
    }

    /** 设置上传文件的最大值 */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 *1024;

    /** 限制上传文件的类型 */
    public static final List<String> AVATAR_TYPE =  new ArrayList<>();
    static{
        AVATAR_TYPE.add("images/jpeg");
        AVATAR_TYPE.add("images/png");
        AVATAR_TYPE.add("images/bmp");
        AVATAR_TYPE.add("images/gif");
    }

    /**
     * MultipartFile接口是SpringMVC提供的一个接口 这个接口为我们包装了获取文件类型的数据(任何类型的file都可以接收)
     * Springboot它又整合了SpringMVC 只需要在处理请求的方法上声明一个参数类型为MultipartFile的参数 然后Springboot自动将传递给服务的文件数据赋值给这个参数
     * @param uid
     * @param file
     * @return
     */
    @PostMapping("/change_avatar")
    public Result changeAvatar(Integer uid,String username, @RequestParam("file") MultipartFile file) throws IOException {
        // 文件是否为null
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize() >AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        //判断文件的类型是否是我们规定的后缀类型
        String contentType = file.getContentType();
        //如果集合包含某个元素则返回true
        if(AVATAR_TYPE.contains(contentType)){
            throw new FileTypeNotMatchException("文件类型不支持");
        }
        //获取原始文件名字
        String originalFilename = file.getOriginalFilename();

        //构建新的文件名
        String extname = originalFilename.substring(originalFilename.lastIndexOf(".")); // 扩展名
        String newFileName = UUID.randomUUID().toString() + extname; //随机名+文件扩展名

        String avatar = "C:\\Users\\EndUser\\Desktop\\Computing\\back end(Java)\\Project\\computerStore\\images\\" + newFileName;
        //将文件存储在服务器的磁盘目录
        try {
            file.transferTo(new File(avatar));
        }catch (FileStatusException e){
            throw new FileStatusException("文件状态异常");
        } catch(IOException e){
            throw new FileUploadException("文件读写异常");
        }
        //返回头像的路径
        userService.changeAvatar(uid,avatar,username);
        //返回用户头像的路径给前端页面 将来用于头像展示使用
        return Result.success(200,avatar);
    }
}
