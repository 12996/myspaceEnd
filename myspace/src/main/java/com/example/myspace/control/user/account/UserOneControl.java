package com.example.myspace.control.user.account;

import com.example.myspace.pojo.User;
import com.example.myspace.service.user.account.UserOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserOneControl {
    @Autowired
    UserOneService userOneService;
    @GetMapping("/user/account/queryOne/")
    public User selectOne(@RequestParam Map<String,String> map){
        Integer id = Integer.parseInt(map.get("userId"));
        return userOneService.getOne(id);
    }
}
