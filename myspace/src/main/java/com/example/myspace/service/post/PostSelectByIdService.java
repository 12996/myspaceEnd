package com.example.myspace.service.post;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface PostSelectByIdService {
    public JSONObject getPostById(Integer id);
}
