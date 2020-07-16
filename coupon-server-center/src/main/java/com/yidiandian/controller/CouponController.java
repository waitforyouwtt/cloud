package com.yidiandian.controller;

import com.yidiandian.entity.Coupon;
import com.yidiandian.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: luoxian
 * @Date: 2020/7/15 14:31
 * @Email: 15290810931@163.com
 */
@RestController
public class CouponController {

    @Autowired
    CouponService couponService;

    @GetMapping("/getip")
    public String getIp(HttpServletRequest request){
        return request.getRemoteHost()+":"+request.getRemotePort();
    }

    @PostMapping("/coupon")
    public Coupon coupon(@RequestBody Coupon coupon){
        return couponService.insert(coupon);
    }
}
