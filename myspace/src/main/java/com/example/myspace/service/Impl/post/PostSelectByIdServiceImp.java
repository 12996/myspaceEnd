package com.example.myspace.service.Impl.post;

import com.alibaba.fastjson.JSONObject;
import com.example.myspace.mapper.PostMapper;
import com.example.myspace.mapper.UserMapper;
import com.example.myspace.pojo.Post;
import com.example.myspace.pojo.User;
import com.example.myspace.service.post.PostSelectByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PostSelectByIdServiceImp implements PostSelectByIdService {
    @Autowired
    PostMapper postMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public JSONObject getPostById(Integer id) {

        JSONObject resp = new JSONObject();
        Post post = postMapper.selectById(id);
        if(post != null) {
            User user = userMapper.selectById(post.getUserId());
            resp.put("username", user.getUsername());
            resp.put("userphoto", user.getPhoto());
            resp.put("userid", String.valueOf(user.getId()));
            resp.put("post", post);
        }
        return resp;
    }
}
