package com.zjl.webshop.entity;/**
 * @Auther: zhou
 * @Date: 2018/12/20 09:47
 * @Description:
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @ClassName Role
 * @Description 角色实体类
 * @Author zhou
 * Date 2018/12/20 9:47
 * @Version 1.0
 **/
@Entity
public class Role implements Serializable {

    /**角色主键*/
    @Id
    @GeneratedValue
    private int roleId;
    /**角色名称*/
    private String roleName;
    /**角色描述*/
    private String roleDescrtption;
    /**状态*/
    private boolean available;

    public Role() {
    }

    public Role(String roleName, String roleDescrtption, boolean available) {
        this.roleName = roleName;
        this.roleDescrtption = roleDescrtption;
        this.available = available;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescrtption() {
        return roleDescrtption;
    }

    public void setRoleDescrtption(String roleDescrtption) {
        this.roleDescrtption = roleDescrtption;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDescrtption='" + roleDescrtption + '\'' +
                ", available=" + available +
                '}';
    }
}
