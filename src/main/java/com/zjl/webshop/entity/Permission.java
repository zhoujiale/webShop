package com.zjl.webshop.entity;/**
 * @Auther: zhou
 * @Date: 2018/12/20 09:55
 * @Description:
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *@ClassName Permission
 *@Description 权限类
 *@Author zhou
 *Date 2018/12/20 9:55
 *@Version 1.0
 **/
@Entity
public class Permission implements Serializable{

    /**权限主键*/
    @Id
    @GeneratedValue
    private int permissionId;
    /**权限名称*/
    private String permissionName;
    /**权限描述*/
    private String permissionDescription;
    /**状态*/
    private boolean avaliable;

    public Permission() {
    }

    public Permission(String permissionName, String permissionDescription, boolean avaliable) {
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
        this.avaliable = avaliable;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public boolean isAvaliable() {
        return avaliable;
    }

    public void setAvaliable(boolean avaliable) {
        this.avaliable = avaliable;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                ", avaliable=" + avaliable +
                '}';
    }
}
