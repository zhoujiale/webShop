package com.zjl.webshop.utils;/**
 * @Auther: bee
 * @Date: 2018/11/27 11:00
 * @Description:
 */

import org.springframework.stereotype.Component;

/**
 *@ClassName StringUtil
 *@Description String工具类
 *@Author zjl
 *Date 2018/11/27 11:00
 *@Version 1.0
 **/
@Component
public class StringUtil {

    /**昵称最短长度*/
    private static int nickNameMin = 2;
    /**昵称最长长度*/
    private static int nickNameMax = 16;


    /**
     * @description 字符串判空
     * @author zhou
     * @created  2018/11/30 11:07
     * @param str 字符串
     * @return
     */
    public static boolean isEmpty(String str){
        if(str.equals("")||str == null){
            return true;
        }
        return false;
    }

    /**
     * @description 字符串密码判断长度
     * @author zhou
     * @created  2018/11/30 11:08
     * @param password 密码
     * @return
     */
    public static boolean isPassword(String password){
        /**必须包含大小写字母和数字的组合，可以使用特殊字符*/
        String reg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,16}$";
        if(password.matches(reg)){
           return false;
        }
        return true;
    }

    /**
     * @description 验证邮箱
     * @author zhou
     * @created  2018/11/30 14:05
     * @param email 邮箱
     * @return
     */
    public static boolean isEmail(String email){
        String reg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        if(email.matches(reg)){
           return false;
        }
        return true;
    }

    /**
     * @description 判断是不是正确的昵称
     * @author zhou
     * @created  2018/12/20 10:21
     * @param nickName 昵称
     * @return 
     */
    public static boolean isNickName(String nickName){
        /**只含有汉字、数字、字母、下划线不能以下划线开头和结尾*/
       String reg = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
       if(nickName.length()>=nickNameMin&&nickName.length()<=nickNameMax&&nickName.matches(reg)){
            return false;
       }
       return true;
    }


    /**
     * @description 验证交易密码
     * @author zhou
     * @created  2018/12/20 10:48    
     * @param dealPassword 交易密码
     * @return 
     */
    public static boolean isDealPassword(String dealPassword) {
       String reg = "^\\d{6}$";
       if(dealPassword.matches(reg)){
           return false;
       }
       return true;
    }

    /**
     * @description 验证手机
     * @author zhou
     * @created  2018/12/20 10:53    
     * @param phone 手机
     * @return 
     */
    public static boolean isPhone(String phone) {
        String reg = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9]|19[0-9])\\d{8}$";
        if(phone.matches(reg)){
           return false;
        }
        return true;
    }
}
