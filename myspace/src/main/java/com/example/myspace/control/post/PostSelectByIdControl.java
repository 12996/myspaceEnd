package com.example.myspace.control.post;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.myspace.pojo.Post;
import com.example.myspace.service.post.PostSelectByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RestController
public class PostSelectByIdControl {
    @Autowired
    PostSelectByIdService postSelectByIdService;
    @GetMapping("/myspace/post/selectbyid/")
    public JSONObject postSelecrById(@RequestParam Map<String,String > map){
        Integer id = Integer.valueOf(map.get("id"));
       return postSelectByIdService.getPostById(id);
    }
}
