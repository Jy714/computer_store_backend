package com.Jy714.computerStore.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String phone;
    private String email;
    private Integer gender;//'性别:0-女，1-男',
    private String avatar;
}
