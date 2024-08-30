package com.Jy714.computerStore.mapper;

import com.Jy714.computerStore.entity.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {

    /**
     * 根据父代号查询区域信息
     * @param parent
     * @return
     */
    List<District> findByParent(String parent);

    String findNameByCode(String code);


}
