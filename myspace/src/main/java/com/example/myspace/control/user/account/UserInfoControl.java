package com.example.myspace.control.user.account;

import com.example.myspace.service.user.account.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserInfoControl {
    @Autowired
    UserInfoService userInfoService;
    @GetMapping("/user/account/info/")
    public Map<String ,String> getinfo(){
        return  userInfoService.getInfo();
    }
}
