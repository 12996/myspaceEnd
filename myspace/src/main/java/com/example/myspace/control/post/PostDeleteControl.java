package com.example.myspace.control.post;

import com.example.myspace.service.post.PostDeleteService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PostDeleteControl {
    @Autowired
    PostDeleteService postDeleteService;
    @GetMapping("/myspace/post/delete/")
    public Map<String,String> DeletePost(@RequestParam Map<String,String> data){
        Integer id = Integer.valueOf(data.get("id"));
        return postDeleteService.deletePost(id);
    }
}
