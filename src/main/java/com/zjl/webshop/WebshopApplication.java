package com.zjl.webshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description 主程序入口
 * @author zhou
 * @created  2018/12/19 16:39    
 * @param 
 * @return 
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.zjl.webshop.controller","com.zjl.webshop.service",
		"com.zjl.webshop.shiro","com.zjl.webshop.aspect"})
@MapperScan("com.zjl.webshop.dao")
public class WebshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}

}

