package com.zjl.webshop.service;

import java.util.Set;

/**
 * @Auther: zhou
 * @Date: 2018/12/20 15:33
 * @Description:
 */
public interface RoleService {

    /**
     * @description 根据用户名查角色
     * @author zhou
     * @created  2018/12/20 15:39    
     * @param 
     * @return 
     */
    Set<String> findRoleByNickName(String nickName);
}
