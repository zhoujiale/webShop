package com.zjl.webshop.utils;/**
 * @Auther: bee
 * @Date: 2018/11/30 10:06
 * @Description:
 */

import org.springframework.stereotype.Component;

/**
 *@ClassName PhoneUtil
 *@Description 手机号工具类
 *@Author zjl
 *Date 2018/11/30 10:06
 *@Version 1.0
 **/
@Component
public class PhoneUtil {

    /**
     * @description 判断是否是手机号
     * @author zjl
     * @created  2018/11/30 10:16
     * @param phone 手机号
     * @return
     */
    public boolean isPhone(String phone){
        String reg = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        return phone.matches(reg);
    }
}
