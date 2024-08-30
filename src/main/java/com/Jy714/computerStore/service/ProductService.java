package com.Jy714.computerStore.service;

import com.Jy714.computerStore.entity.Product;
import com.Jy714.computerStore.entity.VO.ProductVO;

import java.util.List;

public interface ProductService {
    List<ProductVO> findHotList();

    ProductVO findById(Integer id);
}
