package com.Jy714.computerStore.mapper;

import com.Jy714.computerStore.entity.Cart;
import com.Jy714.computerStore.entity.VO.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTest {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert(){
        Cart cart = new Cart();
        cart.setUid(36);
        cart.setPid(10000011);
        cart.setNum(2);
        cart.setPrice(1000L);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid(){
        cartMapper.updateNumberByCid(27,4,"管理员",new Date());
    }

    @Test
    public void findVOByUid(){
        System.out.println( cartMapper.findVOByUid(36));
    }

    @Test
    public void findVOByCid(){
        List<Integer> cids = new ArrayList<>();
        cids.add(27);
        cids.add(28);
        List<CartVO> list = cartMapper.findVOByCid(cids);
        for (CartVO cart: list) {
            System.out.println(cart);
        }
    }

}
