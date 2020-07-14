package com.yidiandian.entity;


import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户信息表(UserInfo)实体类
 *
 * @author makejava
 * @since 2020-07-14 11:36:10
 */
@EqualsAndHashCode
@Accessors(chain = true)

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 975624080298299728L;
    
    private Integer id;
    /**
    * 用户Id
    */
    private String userId;
    /**
    * 用户名
    */
    private String userName;
    /**
    * 昵称
    */
    private String nickName;
    /**
    * 用户密码
    */
    private String password;
    /**
    * 住址
    */
    private String address;
    /**
    * 性别
    */
    private String gender;
    /**
    * 生日
    */
    private Date birthday;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 删除标志
    */
    private String deleteFlag;
    /**
    * 登陆时间
    */
    private Date loginTime;
    /**
    * 修改时间
    */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}