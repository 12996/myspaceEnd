package com.example.myspace.control.user.account;

import com.example.myspace.service.user.account.ResignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ResignControl {
    @Autowired
    private ResignService resignService;
    @PostMapping("/user/account/resign/")
    public Map<String ,String> resign(@RequestParam Map<String,String> data){
        return  resignService.resign(data);
    }
}
