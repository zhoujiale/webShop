package com.zjl.webshop.service;/**
 * @Auther: zhou
 * @Date: 2018/12/20 11:02
 * @Description:
 */

import com.zjl.webshop.entity.Customer;
import com.zjl.webshop.response.WebResponse;

/**
 *@ClassName CustomerService
 *@Description 顾客接口类
 *@Author zhou
 *Date 2018/12/20 11:02
 *@Version 1.0
 **/
public interface CustomerService {
    /**
     * @description 顾客注册
     * @author zhou
     * @created  2018/12/20 11:11    
     * @param customer
     * @return 
     */
    WebResponse customerSign(Customer customer);




    Customer findByNickName(String nickName);
}
