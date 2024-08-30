package com.Jy714.computerStore.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePassword {
    private Integer uid;
    private String username;
    private String oldPassword;
    private String newPassword;
}
