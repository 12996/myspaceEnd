package com.example.myspace.control.post;

import com.alibaba.fastjson.JSONObject;
import com.example.myspace.pojo.Post;
import com.example.myspace.service.post.PostSelectIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PostSelectControl {
    @Autowired
    PostSelectIdService postSelectIdService;

    @GetMapping("/post/selectuserid/")
    public JSONObject selectUserid(@RequestParam Map<String ,String> map){
        Integer userId = Integer.valueOf(map.get("userId"));
        Integer page = Integer.valueOf(map.get("page"));
        return postSelectIdService.SelectUserId(userId, page);
    }
}
