package com.zjl.webshop.service;

import java.util.Set;

/**
 * @Auther: zhou
 * @Date: 2018/12/20 17:57
 * @Description: 角色接口
 */
public interface PermissionService {
    /**
     * @description 根据昵称查询权限
     * @author zhou
     * @created  2018/12/20 17:59    
     * @param nickName
     * @return 
     */
    Set<String> findPermissionByNickName(String nickName);
}
