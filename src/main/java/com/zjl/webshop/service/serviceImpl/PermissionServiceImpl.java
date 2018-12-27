package com.zjl.webshop.service.serviceImpl;/**
 * @Auther: zhou
 * @Date: 2018/12/20 17:57
 * @Description:
 */

import com.zjl.webshop.dao.PermissionDao;
import com.zjl.webshop.service.PermissionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *@ClassName PermissionServiceImpl
 *@Description 角色接口实现类
 *@Author zhou
 *Date 2018/12/20 17:57
 *@Version 1.0
 **/
@Service
public class PermissionServiceImpl implements PermissionService{

    private static Logger log = Logger.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionDao permissionDao;
    /**
     * @description 根据昵称查询权限
     * @author zhou
     * @created  2018/12/20 18:00
     * @param nickName
     * @return 
     */
    @Override
    public Set<String> findPermissionByNickName(String nickName) {
        List<String> permissionList = permissionDao.findPermissionByNickName(nickName);
        Set<String> permissionSet = new HashSet<>(permissionList);
        return permissionSet;
    }
}
