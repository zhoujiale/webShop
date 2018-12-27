package com.zjl.webshop.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Auther: zhou
 * @Date: 2018/12/20 17:54
 * @Description: 权限dao
 */
@Repository
public interface PermissionDao {


    @Select("select permission_name from customer_role_permission crp" +
            " left join customer_permission up on crp.permission_id = up.permission_id" +
            "   where crp.role_id = (" +
            "     select acr.role_id " +
            "     from auth_customer_role acr left join " +
            "       (select * from customer_role where available = 1) cr" +
            "          on  acr.role_id = cr.role_id" +
            "      where acr.customer_id = (" +
            "        select customer_id from customer where nick_name = #{nickName}))")
    @Results({
            @Result(property = "permissionName",column = "permission_name")
    })
    List<String> findPermissionByNickName(String nickName);
}
