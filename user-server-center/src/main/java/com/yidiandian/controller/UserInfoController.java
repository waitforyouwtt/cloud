package com.yidiandian.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: luoxian
 * @Date: 2020/7/14 8:28
 * @Email: 15290810931@163.com
 */
@RestController
public class UserInfoController {

    @GetMapping("/getIp")
    public String getiP(HttpServletRequest request){
        return request.getRemotePort()+"";
    }
}
