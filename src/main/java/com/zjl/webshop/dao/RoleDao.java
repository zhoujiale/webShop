package com.zjl.webshop.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2018/12/20 16:37
 * @Description: 角色dao
 */
@Repository
public interface RoleDao {


    /**
     * @description 根据昵称查询顾客的角色
     * @author zhou
     * @created  2018/12/20 17:47    
     * @param 
     * @return 
     */
    @Select("select role_name" +
            " from auth_customer_role acr left join" +
            "   (select * from customer_role where available = 1) cr" +
            "    on acr.role_id = cr.role_id" +
            " where acr.customer_id = (" +
            "    select customer_id from customer where nick_name = #{nickName})")
    @Results({
            @Result(property = "roleName",column = "role_name")
    })
    List<String> findRoleByNickName(String nickName);
}
