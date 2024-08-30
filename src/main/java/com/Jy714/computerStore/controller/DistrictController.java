package com.Jy714.computerStore.controller;

import com.Jy714.computerStore.entity.District;
import com.Jy714.computerStore.service.DistrictService;
import com.Jy714.computerStore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/districts")
public class DistrictController extends BaseController{

    @Autowired
    private DistrictService districtService;

    // district开头的方法都被拦截到getByParent()方法
    @GetMapping("/parent")
    public Result getByParent(String parent){
        List<District> list = districtService.getByParent(parent);
        return Result.success(200,list);
    }

    /**
     * 处理查询省市区名称的请求
     * @param code
     * @return
     */
    @GetMapping
    public String queryDistrictNameByCode(String code){
        return districtService.getNameByCode(code);
    }
}
