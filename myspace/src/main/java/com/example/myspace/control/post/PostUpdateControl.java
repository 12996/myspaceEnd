package com.example.myspace.control.post;

import com.example.myspace.service.post.PostUpdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PostUpdateControl {
    @Autowired
    PostUpdataService postUpdataService;
    @PostMapping("/myspace/post/update/")
    public Map<String, String> postUpdate(@RequestParam Map<String, String> data){
        return postUpdataService.updatePost(data);
    }
}
