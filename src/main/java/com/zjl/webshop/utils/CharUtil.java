package com.zjl.webshop.utils;/**
 * @Auther: bee
 * @Date: 2018/11/30 10:38
 * @Description:
 */

import org.springframework.stereotype.Component;

/**
 *@ClassName CharUtil
 *@Description 字符工具类
 *@Author zhou
 *Date 2018/11/30 10:38
 *@Version 1.0
 **/
@Component
public class CharUtil {

    /**
     * @description 判断是否是合法昵称
     * @author zhou
     * @created  2018/11/30 10:42
     * @param nickName 昵称
     * @return
     */
    public boolean isNickname(String nickName){
      String reg = "^[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,10}";
      return nickName.matches(reg);
    }


}
