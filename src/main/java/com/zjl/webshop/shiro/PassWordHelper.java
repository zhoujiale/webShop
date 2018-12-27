package com.zjl.webshop.shiro;/**
 * @Auther: zhou
 * @Date: 2018/12/21 09:25
 * @Description:
 */

import com.zjl.webshop.entity.Customer;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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

    @Value("MD5")
    private  String algorithmName;
    @Value("2")
    private int hashIterations;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator){
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName){
       this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations){
       this.hashIterations = hashIterations;
    }

    public Customer encryptPassword(Customer customer){
        if(customer.getSalt() == null || "".equals(customer.getSalt())){
             customer.setSalt(randomNumberGenerator.nextBytes().toHex());
        }
        String newPassword = new SimpleHash(algorithmName,customer.getPassword(),
                ByteSource.Util.bytes(customer.getSalt()),hashIterations).toHex();
        customer.setPassword(newPassword);
        return customer;
    }

}
