package com.Jy714.computerStore;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//MapperScan注解指定当前项目中的Mapper接口路径的位置 在启动项目的时候会自动加载
@MapperScan("com.Jy714.computerStore.mapper")
public class ComputerStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComputerStoreApplication.class, args);
	}

}
