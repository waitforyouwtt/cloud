package com.yidiandian.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Coupon)实体类
 *
 * @author makejava
 * @since 2020-07-16 16:23:11
 */
public class Coupon implements Serializable {
    private static final long serialVersionUID = 168628746342812851L;
    
    private Integer id;
    /**
    * 用户id
    */
    private Integer userId;
    /**
    * 优惠券名称
    */
    private String couponName;
    /**
    * 发放日期
    */
    private Date grantDate;
    /**
    * 是否已使用：1 是，否
    */
    private Integer isUse;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

}