package com.Jy714.computerStore.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void create(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(27);
        list.add(28);
        orderService.create(23,36,"红红",list);
    }
}
