package com.Jy714.computerStore.service;

import com.Jy714.computerStore.entity.VO.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Test
    public void addToCart(){
        cartService.addToCart(36,10000011,3,"Jeremy");
    }

    @Test
    public void getVOByCids(){
        ArrayList<Integer> cids = new ArrayList<>();
        cids.add(20);
        cids.add(27);
        cids.add(28);
        List<CartVO> list = cartService.getVOByCid(36, cids);
        for (CartVO cart: list) {
            System.out.println(cart);
        }
    }
}
