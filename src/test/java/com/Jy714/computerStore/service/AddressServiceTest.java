package com.Jy714.computerStore.service;

import com.Jy714.computerStore.entity.Address;
import com.Jy714.computerStore.entity.VO.AddressVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

//    @Test
//    public void addNewAddress(){
//        Address address = new Address();
//        address.setUid(33);
//        address.setPhone("1234567890");
//        address.setName("alise chen");
//        addressService.addNewAddress(33,"管理员",address);
//        System.out.println("OK");
//    }

    @Test
    public void getByUid(){
        List<AddressVO> list = addressService.getByUid(33);
        for (AddressVO a: list) {
            System.out.println(a);
        }
    }

    @Test
    public void updateAddress(){
        addressService.setDefault(25,36,"管理员");
        System.out.println("OK");
    }
}
