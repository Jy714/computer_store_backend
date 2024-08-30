package com.Jy714.computerStore.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressVO {
    private Integer aid;
    private Integer uid;
    private String name;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String zip;
    private String address;
    private String phone;

}
