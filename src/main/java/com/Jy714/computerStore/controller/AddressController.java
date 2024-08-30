package com.Jy714.computerStore.controller;

import com.Jy714.computerStore.entity.Address;
import com.Jy714.computerStore.entity.DTO.AddressDTO;
import com.Jy714.computerStore.entity.VO.AddressVO;
import com.Jy714.computerStore.service.AddressService;
import com.Jy714.computerStore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController extends BaseController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add_new_address")
    public Result addNewAddress(@RequestBody AddressDTO addressDTO){
        addressService.addNewAddress(addressDTO);
        return Result.success(200,"success");
    }

    @GetMapping
    public Result getByUid(Integer uid){
        List<AddressVO> list = addressService.getByUid(uid);
        return Result.success(200,list);
    }

    @GetMapping("/{aid}/set_default")
    public Result setDefault(@PathVariable Integer aid,Integer uid, String username){
        addressService.setDefault(aid,uid,username);
        return Result.success(200,"success");
    }

    @DeleteMapping("/{aid}/delete")
    public Result delete(@PathVariable Integer aid,Integer uid,String username){
        addressService.delete(aid,uid,username);
        return Result.success(200,"success");
    }
}
