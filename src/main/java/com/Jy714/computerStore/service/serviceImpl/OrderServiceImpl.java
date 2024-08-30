package com.Jy714.computerStore.service.serviceImpl;

import com.Jy714.computerStore.entity.Address;
import com.Jy714.computerStore.entity.Order;
import com.Jy714.computerStore.entity.OrderItem;
import com.Jy714.computerStore.entity.VO.CartVO;
import com.Jy714.computerStore.mapper.OrderMapper;
import com.Jy714.computerStore.service.AddressService;
import com.Jy714.computerStore.service.CartService;
import com.Jy714.computerStore.service.OrderService;
import com.Jy714.computerStore.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;


    @Override
    public Order create(Integer aid, Integer uid, String username, List<Integer> cids) {
        // 即将下单的列表
        List<CartVO> list = cartService.getVOByCid(uid, cids);
        // 计算商品的总价
        Long totalPrice = 0L;
        for (CartVO cart:list) {
            totalPrice += cart.getRealPrice() * cart.getNum();
        }

        Address address = addressService.getByAid(aid, uid);

        Order order = new Order();
        order.setUid(uid);
        // 收货地址数据
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityCode());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        //支付, 总价
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        // 日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());
        // 插入数据
        Integer rows = orderMapper.insertOrder(order);
        if(rows != 1){
            throw new InsertException("插入数据异常");
        }

        for (CartVO cart:list) {
            // 创建一个订单项数据对象
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cart.getPid());
            orderItem.setTitle(cart.getTitle());
            orderItem.setImage(cart.getImage());
            orderItem.setPrice(cart.getRealPrice());
            orderItem.setNum(cart.getNum());
            //日志字段
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());
            //插入数据操作
            orderMapper.insertOrderItem(orderItem);
        }
        // 删除购物车里已购买的商品
        cartService.deleteByCid(cids, uid);
        return order;
    }
}
