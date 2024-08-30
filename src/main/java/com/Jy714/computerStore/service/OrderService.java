package com.Jy714.computerStore.service;

import com.Jy714.computerStore.entity.Address;
import com.Jy714.computerStore.entity.Order;

import java.util.List;

public interface OrderService {
    Order create(Integer aid, Integer uid, String username, List<Integer> cids);
}
