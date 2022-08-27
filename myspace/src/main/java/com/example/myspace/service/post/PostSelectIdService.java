package com.example.myspace.service.post;

import com.alibaba.fastjson.JSONObject;
import com.example.myspace.pojo.Post;

import java.util.List;

public interface PostSelectIdService {
    JSONObject SelectUserId(Integer userId, Integer page);
}
