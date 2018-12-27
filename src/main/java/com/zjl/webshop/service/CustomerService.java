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

    /**
     * @description 根据昵称查顾客
     * @author zhou
     * @created  2018/12/27 10:39    
     * @param 
     * @return 
     */
    Customer findByNickName(String nickName);

    /**
     * @description 增加交易密码
     * @author zhou
     * @created  2018/12/27 10:49
     * @param
     * @param reDealPassword
     * @return
     */
    WebResponse addDealPassword(String nickName, String dealPassword, String reDealPassword);

    /**
     * @description 编辑顾客
     * @author zhou
     * @created  2018/12/27 13:58    
     * @param customer
     * @return 
     */
    WebResponse editCustomer(Customer customer);
}
