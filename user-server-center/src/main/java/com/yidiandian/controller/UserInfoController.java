package com.yidiandian.controller;

import com.yidiandian.entity.UserInfo;
import com.yidiandian.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Book;
import java.util.List;

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
        return userInfoService.queryAllByLimit(0,1);
    }

    @PostMapping(value = "/userInfo")
    public UserInfo saveItem(@RequestBody UserInfo userInfo){
        return userInfoService.insert(userInfo);
    }
}
