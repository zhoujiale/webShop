package com.zjl.webshop.shiro;/**
 * @Auther: zhou
 * @Date: 2018/12/20 14:06
 * @Description:
 */

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *@ClassName ShiroConfig
 *@Description shiro配置容器
 *@Author zhou
 *Date 2018/12/20 14:06
 *@Version 1.0
 **/
@Configuration
public class ShiroConfig {

    /**
     * @description 过滤器
     * @author zhou
     * @created  2018/12/20 14:16    
     * @param securityManager
     * @return 
     */
    @Bean
    public ShiroFilterFactoryBean shrioFilter(SecurityManager securityManager){
         ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
         shiroFilterFactoryBean.setSecurityManager(securityManager);
         //拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/customer/sigin","anon");
        filterChainDefinitionMap.put("/customer/login","anon");
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setUnauthorizedUrl("/index.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * @description 安全管理器
     * @author zhou
     * @created  2018/12/20 14:16    
     * @param 
     * @return 
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * @description 自定义Realm
     * @author zhou
     * @created  2018/12/20 14:16    
     * @param 
     * @return 
     */
    @Bean
    public MyShiroRealm myShiroRealm(){
          MyShiroRealm myShiroRealm = new MyShiroRealm();
          myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
          return myShiroRealm;
    }

    /**
     * @description 注解启用
     * @author zhou
     * @created  2018/12/27 9:35    
     * @param 
     * @return 
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);;
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * @description 密码比较器
     * @author zhou
     * @created  2018/12/27 9:34    
     * @param 
     * @return 
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashIterations(2);
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        return hashedCredentialsMatcher;
    }

    /**
     * @description RememberMe
     * @author zhou
     * @created  2018/12/27 17:32    
     * @param 
     * @return 
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /**
     * @description cookie
     * @author zhou
     * @created  2018/12/27 17:26    
     * @param 
     * @return 
     */
    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(36000);
        return simpleCookie;
    }
}
