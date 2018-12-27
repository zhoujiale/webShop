package com.zjl.webshop.entity;/**
 * @Auther: zhou
 * @Date: 2018/12/20 14:43
 * @Description:
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *@ClassName CustomerRole
 *@Description TODO
 *@Author zhou
 *Date 2018/12/20 14:43
 *@Version 1.0
 **/
@Entity
public class CustomerRole {

    /**主键*/
    @Id
    @GeneratedValue
    private int id;
    /**顾客id*/
    private int customerId;
    /**角色id*/
    private int roleId;

    public CustomerRole() {
    }

    public CustomerRole(int customerId, int roleId) {
        this.customerId = customerId;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "CustomerRole{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", roleId=" + roleId +
                '}';
    }
}
