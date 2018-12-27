package com.zjl.webshop.entity;/**
 * @Auther: zhou
 * @Date: 2018/12/19 17:08
 * @Description:
 */




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 *@ClassName Customer
 *@Description 顾客类
 *@Author zhou
 *Date 2018/12/19 17:08
 *@Version 1.0
 **/
@Entity
public class Customer implements Serializable{

    /**顾客主键*/
    @Id
    @GeneratedValue
    private int customerId;
    /**昵称*/
    private String nickName;
    /**登录密码*/
    private String password;
    /**登录密码的盐*/
    private String salt;
    /**交易密码*/
    private String dealPassword;
    /**交易密码的盐*/
    private String dealSalt;
    /**手机*/
    private String phone;
    /**邮箱*/
    private String email;
    /**地址*/
    private String address;
    /**注册时间*/
    private Date createTime;
    /**状态*/
    private boolean status;

    public Customer() {
    }

    public Customer(String nickName, String password, String salt, String dealPassword, String dealSalt, String phone,
                    String email, String address, Date createTime, boolean status) {
        this.nickName = nickName;
        this.password = password;
        this.salt = salt;
        this.dealPassword = dealPassword;
        this.dealSalt = dealSalt;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.createTime = createTime;
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getDealPassword() {
        return dealPassword;
    }

    public void setDealPassword(String dealPassword) {
        this.dealPassword = dealPassword;
    }

    public String getDealSalt() {
        return dealSalt;
    }

    public void setDealSalt(String dealSalt) {
        this.dealSalt = dealSalt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", dealPassword='" + dealPassword + '\'' +
                ", dealSalt='" + dealSalt + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
