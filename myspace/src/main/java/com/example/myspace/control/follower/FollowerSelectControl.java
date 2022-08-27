package com.example.myspace.control.follower;

import com.example.myspace.service.follower.FollowerSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FollowerSelectControl {
    @Autowired
    FollowerSelectService followerSelectService;
    @PostMapping("/myspace/follower/")
    public Map<String,String> select(@RequestParam Map<String,String> map){
        Integer follower_user = Integer.valueOf(map.get("follower_user"));
        Boolean f = Boolean.valueOf(map.get("f"));
        return followerSelectService.select(follower_user,f);
    }
}
