package com.Jy714.computerStore.mapper;

import com.Jy714.computerStore.entity.Product;
import com.Jy714.computerStore.entity.VO.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 查询热销商品的前4名
     * @return
     */
    List<ProductVO> findHotList();

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    ProductVO findById(Integer id);
}
