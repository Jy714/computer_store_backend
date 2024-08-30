package com.Jy714.computerStore.mapper;

import com.Jy714.computerStore.entity.Cart;
import com.Jy714.computerStore.entity.VO.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CartMapper {

    /**
     * 插入购物车数据
     * @param cart
     * @return
     */
    Integer insert(Cart cart);

    /**
     * 更新购物车某件商品的数量
     * @param cid
     * @param num
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateNumberByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的id和商品的id来查询购物车的数据
     * @param uid
     * @param pid
     * @return
     */
    Cart findByUidAndPid(Integer uid, Integer pid);

    List<CartVO> findVOByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVO> findVOByCid(List<Integer> cids);

    Integer deleteByCids(List<Integer> cids,Integer uid);
}
