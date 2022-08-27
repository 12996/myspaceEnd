package com.example.myspace.control.post;

import com.example.myspace.service.post.PostInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PostInsertControl {
    @Autowired
    PostInsertService postInsertService;
    @PostMapping("/myspace/post/insert/")
    public Map<String ,String> inset(@RequestParam Map<String,String> map){
        return  postInsertService.insert(map);
    }
}
