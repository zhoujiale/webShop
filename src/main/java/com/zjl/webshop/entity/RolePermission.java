package com.zjl.webshop.entity;/**
 * @Auther: zhou
 * @Date: 2018/12/20 14:40
 * @Description:
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *@ClassName RolePermission
 *@Description 角色权限类
 *@Author zhou
 *Date 2018/12/20 14:40
 *@Version 1.0
 **/
@Entity
public class RolePermission {

    /**角色权限主键*/
    @Id
    @GeneratedValue
    private int id;
    /**角色id*/
    private int roleId;
    /**权限id*/
    private int permissionId;

    public RolePermission() {
    }

    public RolePermission(int roleId, int permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
    }
}
