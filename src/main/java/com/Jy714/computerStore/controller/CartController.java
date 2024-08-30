package com.Jy714.computerStore.controller;

import com.Jy714.computerStore.entity.VO.CartVO;
import com.Jy714.computerStore.mapper.CartMapper;
import com.Jy714.computerStore.service.CartService;
import com.Jy714.computerStore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/add_to_cart")
    public Result addToCart(Integer pid, Integer amount, Integer uid, String username){
        // amount 在这里表达数据库中的num
        cartService.addToCart(uid,pid,amount,username);
        return Result.success(200,"success");
    }

    @GetMapping
    public Result findVOByUid(Integer uid){
        List<CartVO> cartVO = cartService.getVOByUid(uid);
        return Result.success(200,cartVO);
    }

    @GetMapping("/{cid}/num/add")
    public Result addNum(@PathVariable Integer cid, Integer uid, String username){
        Integer data = cartService.addNum(cid, uid, username);
        return Result.success(200,data);
    }

    @GetMapping("/{cid}/num/minus")
    public Result minusNum(@PathVariable Integer cid, Integer uid, String username){
        Integer data = cartService.minusNum(cid, uid, username);
        return Result.success(200,data);
    }

    @PostMapping("/list")
    public Result getVOByCid(Integer uid,@RequestParam List<Integer> cids){
        List<CartVO> data = cartService.getVOByCid(uid, cids);
        return Result.success(200,data);
    }
}
