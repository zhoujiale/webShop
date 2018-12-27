package com.zjl.webshop.service.serviceImpl;/**
 * @Auther: zhou
 * @Date: 2018/12/20 15:34
 * @Description:
 */

import com.zjl.webshop.dao.RoleDao;
import com.zjl.webshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *@ClassName RoleServiceImpl
 *@Description 角色接口实现类
 *@Author zhou
 *Date 2018/12/20 15:34
 *@Version 1.0
 **/
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;
    @Override
    public Set<String> findRoleByNickName(String nickName) {
        List<String> roleList = roleDao.findRoleByNickName(nickName);
        Set<String> keySet = new HashSet<>(roleList);
        return keySet;
    }
}
