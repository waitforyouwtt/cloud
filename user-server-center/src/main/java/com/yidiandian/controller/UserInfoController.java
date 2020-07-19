package com.yidiandian.controller;

import com.yidiandian.entity.UserInfo;
import com.yidiandian.request.RequestPay;
import com.yidiandian.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @Author: luoxian
 * @Date: 2020/7/14 8:28
 * @Email: 15290810931@163.com
 */
@RestController
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/getIp")
    public String getiP(HttpServletRequest request){
        return request.getRemotePort()+"";
    }

    @GetMapping(value = "/userInfos")
    public List<UserInfo> getItems(){
        return userInfoService.queryAllByLimit(0,10);
    }

    @PostMapping(value = "/userInfo")
    public UserInfo saveItem(@RequestBody UserInfo userInfo){
        return userInfoService.insert(userInfo);
    }

    @PostMapping(value = "/payOfCoupon")
    public void payOfCoupon(@RequestBody RequestPay requestPay) {
        String txNo = UUID.randomUUID().toString();
        requestPay.setTxNo(txNo);
        userInfoService.payOfCoupon(requestPay);
    }

    @GetMapping("/queryByUserId")
    public void queryByUserId(@RequestParam("userId") String userId){
        userInfoService.queryById(userId);
    }
}
