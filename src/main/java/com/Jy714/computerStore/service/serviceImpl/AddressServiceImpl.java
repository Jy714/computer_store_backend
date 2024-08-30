package com.Jy714.computerStore.service.serviceImpl;

import com.Jy714.computerStore.entity.Address;
import com.Jy714.computerStore.entity.DTO.AddressDTO;
import com.Jy714.computerStore.entity.VO.AddressVO;
import com.Jy714.computerStore.mapper.AddressMapper;
import com.Jy714.computerStore.service.AddressService;
import com.Jy714.computerStore.service.DistrictService;
import com.Jy714.computerStore.service.ex.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    // 在添加用户的收货地址的业务层依赖于DistrictService的业务接口
    @Autowired
    private DistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;
    @Override
    public void addNewAddress(AddressDTO addressDTO) {
        //调用收货地址统计方法
        Integer count = addressMapper.countByUid(addressDTO.getUid());
        //来跟最大地址数(20)做比较
        if(count > maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        // 对address对象中的数据进行补全: 省市区
        String provinceName = districtService.getNameByCode(addressDTO.getProvinceCode());
        String cityName = districtService.getNameByCode(addressDTO.getCityCode());
        String areaName = districtService.getNameByCode(addressDTO.getAreaCode());

        addressDTO.setProvinceName(provinceName);
        addressDTO.setCityName(cityName);
        addressDTO.setAreaName(areaName);

        // uid, isDefault
        addressDTO.setUid(addressDTO.getUid());
        Integer isDefault = count == 0 ? 1 : 0; //1表示默认, 0表示不默认
        addressDTO.setIsDefault(isDefault);
        //补全4项日志
        addressDTO.setCreatedUser(addressDTO.getUsername());
        addressDTO.setCreatedTime(new Date());
        addressDTO.setModifiedUser(addressDTO.getUsername());
        addressDTO.setModifiedTime(new Date());

        Address address = new Address();
        BeanUtils.copyProperties(addressDTO,address);

        // 插入收货地址的方法
        Integer rows = addressMapper.insert(address);
        // 判断是否插入成功
        if(rows != 1){
            throw new InsertException("插入用户的收货地址产生未知异常");
        }
    }

    @Override
    public List<AddressVO> getByUid(Integer uid) {
        List<AddressVO> list = addressMapper.findByUid(uid);
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        // 检测当前获取到的收货地址数据的归属
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        // 先将所有的收货地址设置为默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows < 1){
            throw new UpdateException("更新数据产生未知的异常");
        }
        // 将用户选中的某条地址设置为默认收货地址
         rows = addressMapper.updateDefaultByAid(aid,username,new Date());
        if(rows < 1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result == null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if(result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if(rows != 1){
            throw new DeleteException("用户删除数据产生未知的异常");
        }
        Integer count = addressMapper.countByUid(uid);
        if(count == 0){
            //直接终止程序
            return;
        }
        if (result.getIsDefault() == 1){
            //将这条数据中的is_default字符的值设置1
            Address address = addressMapper.findLastModified(uid);
            rows = addressMapper.updateDefaultByAid(address.getAid(),username,new Date());
            if(rows != 1){
                throw new UpdateException("更新数据时产生未知的异常");
            }
        }
    }

    @Override
    public Address getByAid(Integer aid,Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if(address == null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }
}
