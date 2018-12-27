package com.zjl.webshop.dao;

import com.zjl.webshop.entity.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Auther: zhou
 * @Date: 2018/12/21 09:37
 * @Description:
 */
@Repository
public interface CustomerDao {

    /**
     * @description 根据昵称查询顾客
     * @author zhou
     * @created  2018/12/21 9:43    
     * @param 
     * @return 
     */
    Customer findByNickName(String nickName);

    /**
     * @description 注册
     * @author zhou
     * @created  2018/12/24 15:02    
     * @param 
     * @return 
     */
    int addCustomer(Customer newCustomer);

    /**
     * @description 通过昵称查询登录密码
     * @author zhou
     * @created  2018/12/24 15:02    
     * @param nickName 
     * @return 
     */
    @Select("select password from customer where nick_name = #{nickName}")
    String findPasswordByNickName(String nickName);

    /**
     * @description 为顾客生成角色
     * @author zhou
     * @created  2018/12/25 11:20    
     * @param 
     * @return 
     */
    @Insert("insert into auth_customer_role(customer_id,role_id)" +
            " values(#{customerId},#{roleId})")
    int addRole(@Param("customerId") int customerId,@Param("roleId") int roleId);


    /**
     * @description 更新交易密码
     * @author zhou
     * @created  2018/12/27 14:26    
     * @param nickName
     * @param dealPassword
     * @param dealSalt
     * @return 
     */
    @Update("update customer set deal_password = #{dealPassword}," +
            " deal_salt = #{dealSalt} where nick_name = #{nickName}")
    int updateDealPassword(@Param("nickName") String nickName,
                           @Param("dealPassword") String dealPassword,
                           @Param("dealSalt") String dealSalt);

    /**
     * @description 编辑顾客
     * @author zhou
     * @created  2018/12/27 14:44
     * @param 
     * @return 
     */
    int editCustomer(Customer customer);
}
