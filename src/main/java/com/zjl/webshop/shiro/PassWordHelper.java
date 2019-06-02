package com.zjl.webshop.shiro;/**
 * @Auther: zhou
 * @Date: 2018/12/21 09:25
 * @Description:
 */

import com.zjl.webshop.entity.Customer;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *@ClassName PassWordHelper
 *@Description 加盐加密
 *@Author zhou
 *Date 2018/12/21 9:25
 *@Version 1.0
 **/
@Component
public class PassWordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Autowired
    private HashedCredentialsMatcher hashedCredentialsMatcher;

    public Customer encryptPassword(Customer customer){
        if(customer.getSalt() == null || "".equals(customer.getSalt())){
             customer.setSalt(randomNumberGenerator.nextBytes().toHex());
        }
        String newPassword = new SimpleHash(hashedCredentialsMatcher.getHashAlgorithmName(),customer.getPassword(),
                ByteSource.Util.bytes(customer.getSalt()),hashedCredentialsMatcher.getHashIterations()).toHex();
        customer.setPassword(newPassword);
        return customer;
    }

    /**
     * @description 交易密码
     * @author zhou
     * @created  2018/12/27 11:10    
     * @param 
     * @return 
     */
    public String createPassword(String Password,String Salt){
        String newPassword = new SimpleHash(hashedCredentialsMatcher.getHashAlgorithmName(),
                Password,ByteSource.Util.bytes(Salt),
                hashedCredentialsMatcher.getHashIterations()).toHex();
        return newPassword;
    }

}
