package com.Jy714.computerStore.controller;

import com.Jy714.computerStore.entity.Product;
import com.Jy714.computerStore.entity.VO.ProductVO;
import com.Jy714.computerStore.service.ProductService;
import com.Jy714.computerStore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController{
    @Autowired
    private ProductService productService;

    @GetMapping("/hot_list")
    public Result getHotList(){
        List<ProductVO> list = productService.findHotList();
        return Result.success(200,list);
    }

    @GetMapping("/{id}/details")
    public Result findById(@PathVariable Integer id){
        // 调用业务对象执行获取数据
        ProductVO product = productService.findById(id);
        // 返回成功和数据
        return Result.success(200,product);
    }
}
