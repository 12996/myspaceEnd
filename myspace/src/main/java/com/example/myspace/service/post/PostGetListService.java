package com.example.myspace.service.post;

import com.alibaba.fastjson.JSONObject;

public interface PostGetListService {
    JSONObject getPostList(Integer page);
}
