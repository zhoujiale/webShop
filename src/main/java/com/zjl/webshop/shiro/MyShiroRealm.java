package com.zjl.webshop.shiro;/**
 * @Auther: zhou
 * @Date: 2018/12/20 14:10
 * @Description:
 */

import com.zjl.webshop.entity.Customer;
import com.zjl.webshop.service.CustomerService;
import com.zjl.webshop.service.PermissionService;
import com.zjl.webshop.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 *@ClassName MyShiroRealm
 *@Description TODO
 *@Author zhou
 *Date 2018/12/20 14:10
 *@Version 1.0
 **/
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private CustomerService customerService;

    
    /**
     * @description 为当前登录的用户授予角色和权限
     * @author zhou
     * @created  2018/12/20 14:55    
     * @param 
     * @return 
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String nickName = (String) principalCollection.getPrimaryPrincipal();
        Session session = SecurityUtils.getSubject().getSession();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //查询用户的role
        Set<String> roles = roleService.findRoleByNickName(nickName);
        authorizationInfo.setRoles(roles);
        //查询用户的permission
        Set<String> permissions = permissionService.findPermissionByNickName(nickName);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * @description 验证当前的用户
     * @author zhou
     * @created  2018/12/20 18:12    
     * @param authenticationToken
     * @return 
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //获取用户信息
        String nickName = (String)authenticationToken.getPrincipal();
        //从数据库查找
        Customer customer = customerService.findByNickName(nickName);
        if(customer == null){
            //账号不存在
            throw new UnknownAccountException();
        }else if(!customer.isStatus()){
            //账号锁定
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(customer.getNickName(),
                customer.getPassword(), ByteSource.Util.bytes(customer.getSalt()),getName());
        return simpleAuthenticationInfo;
    }
}
