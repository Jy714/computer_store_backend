package com.Jy714.computerStore.service;

import com.Jy714.computerStore.entity.District;

import java.util.List;

public interface DistrictService {

    /**
     * 根据父代号来查询信息(省市区)
     * @param parent
     * @return
     */
    List<District> getByParent(String parent);

    String getNameByCode(String code);
}
