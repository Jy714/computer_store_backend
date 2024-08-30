package com.Jy714.computerStore.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于返回前端所需的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private Integer uid;
    private String username;
    private String avatar;
    private String phone;
    private String email;
    private Integer gender;
}
