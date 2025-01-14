package com.Jy714.computerStore.service.serviceImpl;

import com.Jy714.computerStore.entity.Cart;
import com.Jy714.computerStore.entity.VO.CartVO;
import com.Jy714.computerStore.entity.VO.ProductVO;
import com.Jy714.computerStore.mapper.CartMapper;
import com.Jy714.computerStore.mapper.ProductMapper;
import com.Jy714.computerStore.service.CartService;
import com.Jy714.computerStore.service.ex.*;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    /** 购物车的业务层依赖于购物车的持久层以及商品的持久层 */
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        //查询当前要添加的这个购物是否已经存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if(result == null){ // 表示这个商品从来没有被添加到购物车中, 则进行新增操作
            // 创建一个cart对象
            Cart cart = new Cart();
            //补全数据: 参数传递的数据
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 价格: 来自于商品中的数据
            ProductVO product = productMapper.findById(pid);
            // 判断是否查到商品的数据
            if(product == null){
                throw new ProductNotFoundException("商品数据不存在");
            }
            // 补全价格
            cart.setPrice(product.getPrice());
            //补全4项日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);
            // 执行输入的插入操作
            Integer rows = cartMapper.insert(cart);
            if(rows != 1){
                throw new InsertException("插入数据时产生未知的异常");
            }
        }else { // 表示当前的商品在购物车中已经存在, 则更新这条数据的num值
            Integer rows = cartMapper.updateNumberByCid(result.getCid(), amount, username, date);
            if(rows != 1){
                throw new UpdateException("更新数据时产生了未知的异常");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null){
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer rows = cartMapper.updateNumberByCid(cid, 1, username, new Date());
        if(rows != 1){
            throw new UpdateException("更新数据失败");
        }
        //返回新的购物车数据的总量
        return result.getNum() + 1;
    }

    @Override
    public Integer minusNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null){
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer rows = cartMapper.updateNumberByCid(cid, -1, username, new Date());
        if(rows != 1){
            throw new UpdateException("更新数据失败");
        }
        //返回新的购物车数据的总量
        return result.getNum() -1;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, List<Integer> cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        for (CartVO cart: list) {
           if(!cart.getUid().equals(uid) ){
               list.remove(cart);
           }
        }
        return list;
    }

    @Override
    public Integer deleteByCid(List<Integer> cids,Integer uid) {
        return cartMapper.deleteByCids(cids, uid);
    }
}
