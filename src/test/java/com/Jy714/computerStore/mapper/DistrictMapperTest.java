package com.Jy714.computerStore.mapper;

import com.Jy714.computerStore.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTest {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> district = districtMapper.findByParent("210100");
        for (District d:district) {
            System.out.println(d);
        }
    }
}
