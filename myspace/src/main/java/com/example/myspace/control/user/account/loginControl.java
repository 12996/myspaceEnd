package com.example.myspace.control.user.account;

import com.example.myspace.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class loginControl {
    @Autowired
    LoginService loginService;
    @PostMapping("/user/account/token/")
    public Map<String,String> getToken(@RequestParam Map<String,String> map){
        String  username = map.get("username");
        String password = map.get("password");
        return  loginService.getToken(username, password);
    }
}
