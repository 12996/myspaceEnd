package com.example.myspace.control.post;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspace.mapper.PostMapper;
import com.example.myspace.pojo.Post;
import com.example.myspace.service.post.PostGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PostListControl {
    @Autowired
    PostGetListService postGetListService;
    @GetMapping("/myspace/post/all/")
    public JSONObject getPostList(@RequestParam Map<String, String> data){
        Integer page = Integer.valueOf(data.get("page"));
        System.out.println(page);
        return postGetListService.getPostList(page);
    }
}
