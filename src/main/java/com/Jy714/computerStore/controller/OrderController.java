package com.Jy714.computerStore.controller;

import com.Jy714.computerStore.entity.Order;
import com.Jy714.computerStore.service.OrderService;
import com.Jy714.computerStore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Result create(Integer aid, @RequestParam List<Integer> cids, Integer uid, String username){
        Order data = orderService.create(aid, uid, username, cids);
        return Result.success(200,data);
    }
}
