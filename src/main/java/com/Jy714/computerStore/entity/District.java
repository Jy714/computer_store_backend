package com.Jy714.computerStore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 省市区的数据实体类 */
public class District{
    private Integer id;
    private String parent;
    private String code;
    private String name;
}
