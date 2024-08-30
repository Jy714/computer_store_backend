package com.Jy714.computerStore.service;

import com.Jy714.computerStore.entity.Address;
import com.Jy714.computerStore.entity.DTO.AddressDTO;
import com.Jy714.computerStore.entity.VO.AddressVO;

import java.util.List;

/** 收货地址业务层接口 */
public interface AddressService {

    void addNewAddress(AddressDTO addressDTO);

    List<AddressVO> getByUid(Integer uid);

    /**
     * 修改某个用户的某条收货地址数据为默认收货地址
     * @param aid
     * @param uid
     * @param username
     */
    void setDefault(Integer aid, Integer uid, String username);

    /**
     * 删除用户选中的收货地址数据
     * @param aid
     * @param uid
     * @param username
     */
    void delete(Integer aid,Integer uid,String username);

    Address getByAid(Integer aid,Integer uid);
}
