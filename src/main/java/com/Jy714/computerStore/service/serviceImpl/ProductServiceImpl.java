package com.Jy714.computerStore.service.serviceImpl;

import com.Jy714.computerStore.entity.Product;
import com.Jy714.computerStore.entity.VO.ProductVO;
import com.Jy714.computerStore.mapper.ProductMapper;
import com.Jy714.computerStore.service.ProductService;
import com.Jy714.computerStore.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductVO> findHotList() {
        List<ProductVO> list = productMapper.findHotList();
        return list;
    }

    @Override
    public ProductVO findById(Integer id) {
        // 根据参数id调用私有方法执行查询 获取商品数据
        ProductVO product = productMapper.findById(id);
        // 判断查询结果是否为null
        if(product == null){
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        // 返回查询结果
        return product;
    }
}
