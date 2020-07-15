package com.yidiandian.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: luoxian
 * @Date: 2020/7/15 14:31
 * @Email: 15290810931@163.com
 */
@RestController
public class CouponController {

    @GetMapping("/getip")
    public String getIp(HttpServletRequest request){
        return request.getRemoteHost()+":"+request.getRemotePort();
    }
}
