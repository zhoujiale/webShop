package com.zjl.webshop.aspect;/**
 * @Auther: zhou
 * @Date: 2018/12/25 10:24
 * @Description:
 */

import org.aspectj.lang.JoinPoint;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 *@ClassName paramFeild
 *@Description 获取参数名
 *@Author zhou
 *Date 2018/12/25 10:24
 *@Version 1.0
 **/
@Component
public class paramFeild {

    private static String[] types = {
            "java.lang.Integer", "java.lang.Double",
            "java.lang.Float", "java.lang.Long", "java.lang.Short",
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",
            "java.lang.String", "int", "double", "long", "short", "byte",
            "boolean", "char", "float"
    };

    private static HashMap<String, Class> map = new HashMap<String, Class>() {
        {
            put("java.lang.Integer", int.class);
            put("java.lang.Double", double.class);
            put("java.lang.Float", float.class);
            put("java.lang.Long", long.class);
            put("java.lang.Short", short.class);
            put("java.lang.Boolean", boolean.class);
            put("java.lang.Char", char.class);
        }
    };


    public static String getParamValue(JoinPoint joinPoint){
        StringBuilder stringBuilder = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        for(int i = 0;i<args.length;i++){
            Object arg = args[i];
            //获取对象类型
            String typeName = arg.getClass().getTypeName();
            for(String t:types){
                //判断是否是基础类型
                if(t.equals(typeName)){
                   stringBuilder.append(arg +";");
                }else {
                    //通过反射获取实体类的属性
                    stringBuilder.append(getFieldsValue(arg));
                }
            }
        }
        return stringBuilder.toString();
    }

    private static String getFieldsValue(Object obj) {
        //通过反射获取所有的字段，getFileds()获取public的修饰的字段
        //getDeclaredFields获取private protected public修饰的字段
        Field[] fields = obj.getClass().getDeclaredFields();
        String typeName = obj.getClass().getTypeName();
        for(String t:types){
            if(t.equals(typeName)){
                return "";
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for(Field f:fields){
            //在反射时能访问私有变量
            f.setAccessible(true);
            try{
                for(String str:types){
                    if(f.getType().getName().equals(str)){
                       stringBuilder.append(f.getName()+":"+f.get(obj)+",");
                    }
                }
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    //返回方法的参数名
    private static String[] getFieldsName(JoinPoint joinPoint) throws ClassNotFoundException, NoSuchMethodException {
        String classType = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class<?>[] classes = new Class[args.length];
        for (int k = 0; k < args.length; k++) {
            if (!args[k].getClass().isPrimitive()) {
                //获取的是封装类型而不是基础类型
                String result = args[k].getClass().getName();
                Class s = map.get(result);
                classes[k] = s == null ? args[k].getClass() : s;
            }
        }
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        //获取指定的方法，第二个参数可以不传，但是为了防止有重载的现象，还是需要传入参数的类型
        Method method = Class.forName(classType).getMethod(methodName, classes);
        String[] parameterNames = pnd.getParameterNames(method);
        return parameterNames;
    }
}
