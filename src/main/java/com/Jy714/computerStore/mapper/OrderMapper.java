package com.Jy714.computerStore.mapper;

import com.Jy714.computerStore.entity.Order;
import com.Jy714.computerStore.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/** 订单的持久层接口 */
@Mapper
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param order
     * @return
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单项的数据
     * @param orderItem
     * @return
     */
    Integer insertOrderItem(OrderItem orderItem);

}
