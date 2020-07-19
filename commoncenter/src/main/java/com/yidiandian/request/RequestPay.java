package com.yidiandian.request;

import java.io.Serializable;

/**
 * @Author: luoxian
 * @Date: 2020/7/17 23:48
 * @Email: 15290810931@163.com
 */
public class RequestPay implements Serializable{

    private String txNo;

    private Integer couponId;

    private Integer userId;

    public String getTxNo() {
        return txNo;
    }

    public void setTxNo(String txNo) {
        this.txNo = txNo;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
