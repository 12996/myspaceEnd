package com.example.myspace.control;

import com.example.myspace.mapper.UserMapper;
import com.example.myspace.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class userlist {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/user/userlist/")
    public List<User> getUser(){
        List<User> userList = userMapper.selectList(null);
        return  userList;
    }
}
