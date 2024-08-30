package com.Jy714.computerStore.mapper;

import com.Jy714.computerStore.entity.Address;
import com.Jy714.computerStore.entity.District;
import com.Jy714.computerStore.entity.VO.AddressVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/** 收货地址持久层的接口 */
@Mapper
public interface AddressMapper {
    /**
     * 插入用户的收货地址数据
     * @param address
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户id统计收货地址数量
     * @param uid
     * @return 当前用户的收货地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户的id查询用户的收货地址数据
     * @param uid
     * @return
     */
    List<AddressVO> findByUid(Integer uid);

    /**
     * 根据aid查询收货地址数据
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户uid值来修改用户的收货地址设置为非默认
     * @param uid
     * @return
     */
    Integer updateNonDefault(Integer uid);

    Integer updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * 根据收货地址id删除收货地址数据
     * @param aid
     * @return
     */
    Integer deleteByAid(Integer aid);

    /**
     * 根据用户uid来查询当前用户最后一次被修改的收货地址数据
     * @param uid
     * @return
     */
    Address findLastModified(Integer uid);
}
