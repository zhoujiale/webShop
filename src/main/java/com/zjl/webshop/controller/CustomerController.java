package com.zjl.webshop.controller;/**
 * @Auther: zhou
 * @Date: 2018/12/19 14:05
 * @Description:
 */

import com.zjl.webshop.entity.Customer;
import com.zjl.webshop.response.WebResponse;
import com.zjl.webshop.service.CustomerService;
import com.zjl.webshop.service.PermissionService;
import com.zjl.webshop.service.RoleService;
import com.zjl.webshop.utils.StringUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Set;

/**
 *@ClassName CustomerController
 *@Description 顾客web类
 *@Author zhou
 *Date 2018/12/19 14:05
 *@Version 1.0
 **/
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static Logger log = Logger.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/ok",method = RequestMethod.POST,produces = "application/json")
    public WebResponse test(String name){
        return new WebResponse().ok(name);
    }

    /**
     * @description 顾客注册
     * @author zhou
     * @created  2018/12/19 16:44
     * @param
     * @return
     */
    @RequestMapping(value = "/sign",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public WebResponse customerSign(@RequestBody Customer customer){
        //验空
        if(StringUtil.isEmpty(customer.getNickName())||StringUtil.isEmpty(customer.getPassword())||StringUtil.
                isEmpty(customer.getPassword())||StringUtil.isEmpty(customer.getPhone())||
                StringUtil.isEmpty(customer.getEmail())|StringUtil.isEmpty(customer.getAddress())){
            log.error("参数错误");
            return new WebResponse().error(400,"","参数错误");
        }
        if(StringUtil.isNickName(customer.getNickName())){
            log.error("昵称格式错误");
            return new WebResponse().error(401,"","昵称格式错误");
        }
        if(StringUtil.isPassword(customer.getPassword())){
            log.error("登录密码格式错误");
            return new WebResponse().error(402,"","登录密码格式错误");
        }
        if(StringUtil.isPhone(customer.getPhone())){
            log.error("输入手机号错误");
            return new WebResponse().error(403,"","输入手机号错误");
        }
        if(StringUtil.isEmail(customer.getEmail())){
            log.error("邮箱错误");
            return new WebResponse().error(404,"","邮箱错误");
        }
        WebResponse webResponse = customerService.customerSign(customer);
        return webResponse;
    }

    /**
     * @description 登录
     * @author zhou
     * @created  2018/12/24 18:19    
     * @param 
     * @return 
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public WebResponse login(@RequestParam("nickName") String nickName,@RequestParam("password") String password,
                             @RequestParam(value = "rememberMe",defaultValue = "false")boolean rememberMe,
                             HttpServletRequest request){
        if(StringUtil.isEmpty(nickName)||StringUtil.isEmpty(password)){
             log.error("用户名或密码为空");
             return new WebResponse().error(400,"","用户名或密码为空");
        }
        //查询角色
        Set<String> roleList = roleService.findRoleByNickName(nickName);
        //查询权限
        Set<String> permissionList = permissionService.findPermissionByNickName(nickName);
        //获得主体
        Subject currentUser = SecurityUtils.getSubject();
        //判断用户是否登陆
        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(nickName,password);
            try{
                //token.setRememberME(rememberMe);
                //存入参数
                request.getSession().setAttribute("token",nickName);
                request.getSession().setAttribute("role",roleList);
                request.getSession().setAttribute("permission",permissionList);
                currentUser.login(token);

            }catch (UnknownAccountException uae){
                log.error("账号不存在");
                return new WebResponse().error(402,"","账号不存在");
            }catch (IncorrectCredentialsException ice){
                log.error("密码错误,请重试");
                return new WebResponse().error(403,"","密码错误,请重试");
            }catch (LockedAccountException lae){
                log.error("该账号已被禁用,无法登陆");
                return new WebResponse().error(404,"","该账号已被禁用,无法登陆");
            }catch (AuthenticationException ae){
                log.error("未知错误");
                return new WebResponse().error(405,"","未知错误");
            }
        }
        HashMap<String,Object> customerMap = new HashMap<>();
        customerMap.put("msg",nickName+"登陆成功");
        customerMap.put("role",roleList);
        customerMap.put("permission",permissionList);
        return new WebResponse().ok(customerMap);
    }

    /**
     * @description 退出登录
     * @author zhou
     * @created  2018/12/24 18:20    
     * @param nickName 昵称
     * @return 
     */
    @RequestMapping(value = "/loginOut",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public WebResponse loginOut(String nickName){
        if(StringUtil.isEmpty(nickName)){
            log.error("昵称为空");
            return new WebResponse().error(400,"","昵称为空");
        }
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return new WebResponse().ok(nickName + "退出登录");
    }

    /**
     * @description 添加交易密码
     * @author zhou
     * @created  2018/12/27 9:58    
     * @param nickName 昵称
     * @param dealPassword 交易密码
     * @param reDealPassword 重复交易密码
     * @return 
     */
    @RequestMapping(value = "/addDealPassword")
    @RequiresRoles("customer")
    public WebResponse addDealPassword(String nickName,String dealPassword,String reDealPassword){
        if(StringUtil.isEmpty(nickName)||StringUtil.isEmpty(dealPassword)||StringUtil.isEmpty(reDealPassword)){
            log.error("参数错误");
            return new WebResponse().error(400,"","参数错误");
        }
        if(StringUtil.isDealPassword(dealPassword)){
            log.error("交易密码格式错误");
            return new WebResponse().error(401,"","交易密码格式错误");
        }
        WebResponse webResponse = customerService.addDealPassword(nickName,dealPassword,reDealPassword);
        return webResponse;
    }

    /**
     * @description 编辑个人资料
     * @author zhou
     * @created  2018/12/27 13:50    
     * @param 
     * @return 
     */
    @RequiresRoles("customer")
    @RequestMapping(value = "/editCustomer",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public WebResponse editCustomer(@RequestBody Customer customer){
         if(StringUtil.isEmpty(customer.getPassword())||StringUtil.isEmpty(customer.getPhone())||StringUtil.
                 isEmpty(customer.getEmail())||StringUtil.isEmpty(customer.getAddress())){
             log.error("参数错误");
             return new WebResponse().error(400,"","参数错误");
         }
         if(StringUtil.isPhone(customer.getPhone())){
             log.error("手机号错误");
             return new WebResponse().error(401,"","手机号错误");
         }
         if(StringUtil.isEmail(customer.getEmail())){
             log.error("邮箱错误");
             return new WebResponse().error(402,"","邮箱错误");
         }
         if(StringUtil.isPassword(customer.getPassword())){
            log.error("登录密码错误");
            return new WebResponse().error(403,"","登录密码错误");
         }
         WebResponse webResponse = customerService.editCustomer(customer);
         return webResponse;
    }
}
