package com.example.myspace.control.user.account;


import com.example.myspace.pojo.User;
import com.example.myspace.service.user.account.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateUserControl {
    @Autowired
    UpdateUserService updateUserService;
    @PostMapping("/myspace/user/update/")
    public String updateUser(@RequestParam Map<String, String> data){
        Integer id = Integer.valueOf(data.get("id"));
        Integer attention = 0;
        Integer read_number = 0;
        if(data.get("attention") != null){
           attention = Integer.valueOf(data.get("attention"));
        }
        if(data.get("read_number") != null){
            read_number = Integer.valueOf(data.get("read_number"));
        }
        if(attention != null || read_number != null){
            return updateUserService.updateUser(id, attention, read_number);
        }
        return  null;
    }
}
