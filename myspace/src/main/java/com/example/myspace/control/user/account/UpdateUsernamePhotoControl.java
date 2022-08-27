package com.example.myspace.control.user.account;

import com.example.myspace.service.user.account.UpdateUsernamePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateUsernamePhotoControl {
    @Autowired
    private UpdateUsernamePhotoService updateUsernamePhotoService;

    @PostMapping("/myspace/user/update/usermessage/")
    public Map<String, String> updateUserMessage(@RequestParam Map<String, String> data){
        String event = data.get("event");
        if("username".equals(event)){
            return updateUsernamePhotoService.updateUsernamePhoto(data);
        }else if("password".equals(event)){
            return updateUsernamePhotoService.updatePassword(data);
        }
        return null;
    }
}
